<script setup lang="ts">
import { ref, type Ref, onMounted } from 'vue'
import type { BoardDetailResponseType } from '@/types/board'
import BoardWriterInformation from '@/components/molecules/board/BoardWriterInformation.vue'
import ReplyElement from '@/components/molecules/board/ReplyElement.vue'
import ReplyWriteForm from '@/components/molecules/board/ReplyWriteForm.vue'
import LikeButton from '@/components/molecules/LikeButton.vue'
import TextAtom from '@/components/atoms/TextAtom.vue'
import LineAtom from '@/components/atoms/LineAtom.vue'
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import { HeartOutlined, CommentOutlined } from '@ant-design/icons-vue'

const board: Ref<BoardDetailResponseType> = ref({
    boardNo: 0,
    title: '',
    content: '',
    writer: '',
    writerEmail: '',
    writerProfileUrl: null,
    registerAt: '',
    hit: 0,
    boardCategory: '',
    publicYn: false,
    missionCategory: '',
    likedCount: 0,
    replyCount: 0
})
onMounted(() => {
    const dummyData = {
        boardNo: 1,
        title: 'title1',
        content: `
        What is Lorem Ipsum?
Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.

Why do we use it?
It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).


Where does it come from?
Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of "de Finibus Bonorum et Malorum" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, "Lorem ipsum dolor sit amet..", comes from a line in section 1.10.32.

The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from "de Finibus Bonorum et Malorum" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.

Where can I get some?
There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.
        `,
        writer: 'writer1',
        writerEmail: 'writer@naver.com',
        writerProfileUrl: null,
        registerAt: '2022-03-10T13:22:09',
        hit: 12,
        boardCategory: 'notice',
        publicYn: true,
        missionCategory: '선물사주기',
        likedCount: 0,
        replyCount: 123
    }
    board.value = dummyData
})
</script>

<template>
    <div
        class="flex flex-col w-full md:min-w-[768px] max-w-[1080px] max-md:min-w-0 border md:rounded border-A805DarkGrey p-9"
    >
        <div class="w-full flex flex-col">
            <TextAtom class="text-[28px] mb-2">{{ board.title }}</TextAtom>
            <BoardWriterInformation
                :writer="board.writer"
                :writer-email="board.writerEmail"
                :writer-profile-url="board.writerProfileUrl"
                :register-at="board.registerAt"
                :hit="board.hit"
                :liked-count="board.likedCount"
                :reply-count="board.replyCount"
            />
        </div>
        <LineAtom custom-class="my-4 border-A805LightGrey" />
        <div>
            {{ board.content }}
        </div>
        <div class="mt-[60px] flex gap-[20px] text-[16px]">
            <!-- <ButtonAtom custom-class="flex items-center gap-[6px]"
                ><HeartOutlined class="text-[24px]" /> 좋아요
                <b>{{ board.likedCount }}</b></ButtonAtom
            > -->
            <LikeButton :liked-count="board.likedCount" />
            <span class="flex items-center gap-[6px]"
                ><CommentOutlined class="text-[24px]" /> 댓글 <b>{{ board.replyCount }}</b></span
            >
        </div>
        <LineAtom custom-class="my-4 border-A805LightGrey" />
        <div class="flex flex-col flex-1">
            <TextAtom custom-class="font-bold text-[20px]">댓글</TextAtom>
            <ReplyElement> ㄴㅇㄹ </ReplyElement>
            <ReplyElement> ㄴㅇㄹ </ReplyElement>
            <ReplyElement> ㄴㅇㄹ </ReplyElement>
            <ReplyElement> ㄴㅇㄹ </ReplyElement>
            <ReplyElement> ㄴㅇㄹ </ReplyElement>
            <ReplyWriteForm class="my-5" />
        </div>
    </div>
</template>

<style scoped></style>
