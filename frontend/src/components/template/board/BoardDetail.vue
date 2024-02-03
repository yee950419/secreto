<script setup lang="ts">
import { ref, type Ref, onMounted, computed } from 'vue'
import type { BoardDetailResponseType, ReplyResponseType } from '@/types/board'
import BoardWriterInformation from '@/components/molecules/board/BoardWriterInformation.vue'
import ReplyElement from '@/components/molecules/board/ReplyElement.vue'
import ReplyWriteForm from '@/components/molecules/board/ReplyWriteForm.vue'
import LikeButton from '@/components/molecules/board/LikeButton.vue'
import BoardDetailTop from '@/components/molecules/board/BoardDetailTop.vue'
import BoardDetailBottom from '@/components/molecules/board/BoardDetailBottom.vue'
import TextAtom from '@/components/atoms/TextAtom.vue'
import LineAtom from '@/components/atoms/LineAtom.vue'
import ModalTemplate from '@/components/template/ModalTemplate.vue'
import PostDeleteModalContent from '@/components/organisms/modal/BoardDeleteModalContent.vue'
import { CommentOutlined } from '@ant-design/icons-vue'
import type { Handler } from '@/types/common'
import { getPost, getReplies } from '@/api/board'
import { useRoute } from 'vue-router'

const route = useRoute()
const postId = computed(() => {
    return Number(route.query.postId)
})
const post: Ref<BoardDetailResponseType> = ref({
    boardNo: 0,
    boardCategory: '',
    title: '',
    content: '',
    writer: '',
    writerEmail: '',
    writerProfileUrl: null,
    registerAt: '',
    roomUserNo: 0,
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

onMounted(() => {
    getPost(
        postId.value,
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
    getReplies(
        postId.value,
        (response) => {
            const data = response.data
            if (data.status === 'OK') {
                console.log('========== 댓글 목록 ==========')
                console.log(data.message)
                console.log(data.result)
                replyCount.value = data.result.length
                replies.value = constructParentChildRelation(data.result)
            }
        },
        (error) => alert('댓글 목록 조회 실패')
    )
})

const writeButtonHandler: Handler = () => {
    alert('글쓰기 페이지 이동')
}
const modifyButtonHandler: Handler = () => {
    alert('수정 페이지 이동')
}
const topButtonHandler: Handler = () => {
    alert('맨 이벤트 발생')
}
const listButtonHandler: Handler = () => {
    alert('목록 페이지 이동')
}

// modal
const deleteModalSeen: Ref<boolean> = ref(false)
const deleteModalToggle: Handler = () => {
    deleteModalSeen.value = !deleteModalSeen.value
}
const postDeleteHandler: Handler = () => {
    alert('게시글 삭제 이벤트')
}
</script>

<template>
    <div class="w-full md:min-w-[768px] max-w-[1080px] max-md:min-w-0 bg-A805Rea">
        <BoardDetailTop
            class="my-4 max-md:hidden"
            @modify-button-handle="modifyButtonHandler"
            @delete-button-handle="deleteModalToggle"
            @list-button-handle="listButtonHandler"
        />
        <div
            class="flex flex-col w-full border md:rounded border-A805DarkGrey p-9 max-md:border-x-0"
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
            <div>
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
                    <ReplyElement :reply="reply" :post-writer-user-no="post.roomUserNo" />
                    <ReplyElement
                        v-for="child in reply.children"
                        :reply="child"
                        :key="child.replyNo"
                        :post-writer-user-no="post.roomUserNo"
                        :nested="true"
                    />
                </template>
                <ReplyWriteForm class="mt-5" />
            </div>
        </div>
        <BoardDetailBottom
            class="my-4"
            @write-button-handle="writeButtonHandler"
            @modify-button-handle="modifyButtonHandler"
            @delete-button-handle="deleteModalToggle"
            @top-button-handle="topButtonHandler"
            @list-button-handle="listButtonHandler"
        />

        <!-- Delete Model -->
        <ModalTemplate
            custom-id="modal"
            custom-class="modal-template-style-1 w-[350px]"
            :seen="deleteModalSeen"
            v-if="deleteModalSeen"
            @modal-close="deleteModalToggle"
        >
            <PostDeleteModalContent
                @yes-button-handle="postDeleteHandler"
                @no-button-handle="deleteModalToggle"
            />
        </ModalTemplate>
    </div>
</template>

<style scoped></style>
