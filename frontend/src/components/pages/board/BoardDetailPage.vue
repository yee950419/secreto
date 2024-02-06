<script setup lang="ts">
import { ref, type Ref, onMounted, computed, inject, readonly, provide } from 'vue'
import type { BoardDetailResponseType, BoardRequestType, ReplyResponseType } from '@/types/board'
import BoardWriterInformation from '@/components/molecules/board/BoardWriterInformation.vue'
import ReplyWriteForm from '@/components/molecules/board/ReplyWriteForm.vue'
import LikeButton from '@/components/molecules/board/LikeButton.vue'
import BoardDetailTop from '@/components/molecules/board/BoardDetailTop.vue'
import BoardDetailBottom from '@/components/molecules/board/BoardDetailBottom.vue'
import ReplyElement from '@/components/organisms/board/ReplyElement.vue'
import TextAtom from '@/components/atoms/TextAtom.vue'
import LineAtom from '@/components/atoms/LineAtom.vue'
import ModalTemplate from '@/components/template/ModalTemplate.vue'
import YesNoModalContent from '@/components/organisms/modal/YesNoModalContent.vue'
import { CommentOutlined } from '@ant-design/icons-vue'
import type { Handler } from '@/types/common'
import { getPost, getReplies } from '@/api/board'
import { useRoute } from 'vue-router'
import router from '@/router'
import YesModalContent from '@/components/organisms/modal/YesModalContent.vue'

const route = useRoute()
const boardNo = computed(() => {
    return Number(route.query.boardNo)
})
const boardCategory = computed(() => {
    return String(route.query.boardCategory)
})
const roomNo: Ref<number> = ref(Number(route.params.roomNo))
const roomUserNo = inject<Ref<number>>('roomUserInfo', ref(-1))
const boardRequest: Ref<BoardRequestType> = ref({
    boardCategory: String(route.query.boardCategory),
    title: route.query.title === undefined ? '' : String(route.query.title),
    content: route.query.content === undefined ? '' : String(route.query.content),
    writer: route.query.writer === undefined ? '' : String(route.query.writer),
    page: Number(route.query.page) > 0 ? Number(route.query.page) : 0,
    size: 10
})
provide('boardNo', readonly(boardNo))

const post: Ref<BoardDetailResponseType> = ref({
    boardNo: 0,
    boardCategory: '',
    title: '',
    content: '',
    writer: '',
    writerEmail: '',
    writerProfileUrl: null,
    registerAt: '',
    roomUserNo: -1,
    hit: 0,
    publicYn: false,
    missionCategory: '',
    likedCount: 0
})
const replies: Ref<ReplyResponseType[]> = ref([])
const replyCount: Ref<number> = ref(0)

const constructParentChildRelation = (replies: ReplyResponseType[]): ReplyResponseType[] => {
    const parentIndexer: Map<number, number> = new Map()
    const constructed: ReplyResponseType[] = []
    const children: ReplyResponseType[] = []
    let parentMapSize: number = 0
    for (const reply of replies) {
        if (reply.parentReplyNo === null) {
            constructed.push(reply)
            parentIndexer.set(reply.replyNo, parentMapSize++)
            reply['children'] = []
        } else {
            children.push(reply)
        }
    }
    children.forEach((child) => {
        if (child.parentReplyNo === null) return
        const index = parentIndexer.get(child.parentReplyNo)
        if (index !== undefined) {
            index != constructed[index]['children']?.push(child)
        }
    })
    return constructed
}

const loadReplies = () => {
    getReplies(
        roomNo.value,
        boardNo.value,
        (response) => {
            const data = response.data
            if (data.status === 'OK') {
                console.log('========== 댓글 목록 ==========')
                console.log(data.message)
                replyCount.value = data.result.filter(
                    (reply: ReplyResponseType) => !reply.deleteYn
                ).length
                replies.value = constructParentChildRelation(data.result)
            }
        },
        (error) => alert('댓글 목록 조회 실패')
    )
}

onMounted(() => {
    getPost(
        roomNo.value,
        boardNo.value,
        (response) => {
            const data = response.data
            if (data.status === 'OK') {
                console.log('========== 게시글 상세 ==========')
                console.log(data.message)
                console.log(data.result)
                post.value = data.result
            }
        },
        (error) => alert('게시글 조회 실패')
    )
    loadReplies()
})

const okModalContent: Ref<string> = ref('')
const loadRepliesAndShowModal = (content: string) => {
    okModalContent.value = content
    loadReplies()
    replyDeleteSuccessModalToggle()
}
const modifyButtonHandler: Handler = () => {
    alert('수정 페이지 이동')
}
const topButtonHandler: Handler = () => {
    alert('맨 이벤트 발생')
}
const listButtonHandler: Handler = () => {
    router.push({ name: 'game-board-list', query: { ...boardRequest.value } })
}

// modal
const deleteModalSeen: Ref<boolean> = ref(false)
const deleteModalToggle: Handler = () => {
    deleteModalSeen.value = !deleteModalSeen.value
}
const postDeleteHandler: Handler = () => {
    alert('게시글 삭제 이벤트')
}

const replyDeleteSuccessModalSeen: Ref<boolean> = ref(false)
const replyDeleteSuccessModalToggle = () =>
    (replyDeleteSuccessModalSeen.value = !replyDeleteSuccessModalSeen.value)
</script>

<template>
    <div class="w-full flex justify-center px-5">
        <div class="w-full md:min-w-[768px] max-w-[1400px] max-md:min-w-0">
            <BoardDetailTop
                class="my-4 max-md:hidden"
                @modify-button-handle="modifyButtonHandler"
                @delete-button-handle="deleteModalToggle"
                @list-button-handle="listButtonHandler"
                :is-post-writer="roomUserNo === post.roomUserNo"
            />
            <div
                class="flex flex-col w-full border md:rounded border-A805LightGrey md:p-9 max-md:pt-3 max-md:border-x-0"
            >
                <div class="w-full flex flex-col">
                    <TextAtom class="text-[28px] mb-2">{{ post.title }}</TextAtom>
                    <BoardWriterInformation
                        :writer="post.writer"
                        :writer-email="post.writerEmail"
                        :writer-profile-url="post.writerProfileUrl"
                        :register-at="post.registerAt"
                        :hit="post.hit"
                        :liked-count="post.likedCount"
                        :reply-count="replyCount"
                    />
                </div>
                <LineAtom custom-class="my-4 border-A805LightGrey" />
                <div class="min-h-[150px]">
                    {{ post.content }}
                </div>
                <div class="mt-[60px] flex gap-[20px] text-[16px]">
                    <!-- <ButtonAtom custom-class="flex items-center gap-[6px]"
                ><HeartOutlined class="text-[24px]" /> 좋아요
                <b>{{ post.likedCount }}</b></ButtonAtom
            > -->
                    <LikeButton :liked-count="post.likedCount" />
                    <span class="flex items-center gap-[6px]"
                        ><CommentOutlined class="text-[24px]" /> 댓글 <b>{{ replyCount }}</b></span
                    >
                </div>
                <LineAtom custom-class="my-4 border-A805LightGrey" />
                <div class="flex flex-col flex-1">
                    <TextAtom custom-class="font-bold text-[20px]">댓글</TextAtom>
                    <template v-for="reply in replies" :key="reply.replyNo">
                        <ReplyElement
                            :reply="reply"
                            :post-writer-user-no="post.roomUserNo"
                            @submit-reply-success-handle="
                                () => loadRepliesAndShowModal('댓글을 작성하였습니다.')
                            "
                            @delete-success-handle="
                                () => loadRepliesAndShowModal('댓글이 삭제되었습니다.')
                            "
                        />
                        <ReplyElement
                            v-for="child in reply.children"
                            :reply="child"
                            :key="child.replyNo"
                            :post-writer-user-no="post.roomUserNo"
                            :nested="true"
                            @submit-reply-success-handle="
                                () => loadRepliesAndShowModal('댓글을 작성하였습니다.')
                            "
                            @delete-success-handle="
                                () => loadRepliesAndShowModal('댓글이 삭제되었습니다.')
                            "
                        />
                    </template>
                    <ReplyWriteForm
                        class="mt-5"
                        :postNo="post.boardNo"
                        @submit-reply-success-handle="
                            () => loadRepliesAndShowModal('댓글을 작성하였습니다.')
                        "
                    />
                </div>
            </div>
            <BoardDetailBottom
                class="my-4"
                @write-button-handle="
                    () =>
                        router.push({
                            name: 'game-board-write',
                            query: { boardCategory: boardCategory }
                        })
                "
                @modify-button-handle="modifyButtonHandler"
                @delete-button-handle="deleteModalToggle"
                @top-button-handle="topButtonHandler"
                @list-button-handle="listButtonHandler"
                :is-post-writer="roomUserNo === post.roomUserNo"
            />
        </div>
        <!-- Delete Modal -->
        <ModalTemplate
            custom-id="modal"
            custom-class="modal-template-style-1 w-[350px]"
            :seen="deleteModalSeen"
            v-if="deleteModalSeen"
            @modal-close="deleteModalToggle"
        >
            <YesNoModalContent
                @yes-button-handle="postDeleteHandler"
                @no-button-handle="deleteModalToggle"
                content-message="게시글을 삭제하시겠습니까?"
            />
        </ModalTemplate>

        <!-- Ok Modal -->
        <ModalTemplate
            custom-id="modal"
            custom-class="modal-template-style-1 w-[350px]"
            :seen="replyDeleteSuccessModalSeen"
            v-if="replyDeleteSuccessModalSeen"
            @modal-close="replyDeleteSuccessModalToggle"
        >
            <YesModalContent
                @yes-button-handle="replyDeleteSuccessModalToggle"
                :content-message="okModalContent"
            />
        </ModalTemplate>
    </div>
</template>

<style scoped></style>
