<script setup lang="ts">
import TextAtom from '@/components/atoms/TextAtom.vue'
import HeaderLogo from '@/components/molecules/common/HeaderLogo.vue'
import CharacterAtom from '@/components/atoms/CharacterAtom.vue'
import { onMounted } from 'vue'
import { HomeFilled, UserOutlined, LeftOutlined, RightOutlined } from '@ant-design/icons-vue'
import { ref } from 'vue'
type Guide = {
    img: string
    text: string
}

const showIndex = ref(0)

const setting = ref<Array<Guide>>([
    {
        img: 'src/assets/images/guides/master1.png',
        text: '1. 메인화면 방리스트 조회 항목에서 가장 마지막 영역에 빈 카드에 + 버튼이 있는 카드를 눌러 방만들기를 할 수 있습니다.'
    },
    {
        img: 'src/assets/images/guides/master2.png',
        text: '2. 방만들기 전 방 이름과 방에서 쓸 닉네임을 설정할 수 있습니다.'
    },
    {
        img: 'src/assets/images/guides/master3.png',
        text: '3. 방에 들어오시면 방 설정을 하실 수 있습니다.'
    },
    {
        img: 'src/assets/images/guides/master4.png',
        text: '4. 게임기간, 미션의 주기, 방장의 참여여부, 미션의 종류, 참여 유저 관리를 하실 수 있습니다.'
    },
    {
        img: 'src/assets/images/guides/master5.png',
        text: '5. 게임 시작버튼을 눌러 게임을 시작 하실 수 있습니다.'
    },
    {
        img: 'src/assets/images/guides/master6.png',
        text: '6. 게임이 시작되면 방 설정에서 돌발 미션을 거실수 있어요.'
    },
    {
        img: 'src/assets/images/guides/master7.png',
        text: '7. 게임중 추가 인원 발생시 전체 셔플 또는 끼워넣기를 하실 수 있어요.'
    },
    {
        img: 'src/assets/images/guides/master6.png',
        text: '8. 방 설정에서 게임을 종료하거나 방장을 위임할 수 있어요'
    }
])

const secreto = ref<Array<Guide>>([
    {
        img: 'src/assets/images/member/member1.png',
        text: '1. 이번 시크리또 프로젝트 팀장이자 BE 리더 이상학 입니다.'
    },
    {
        img: 'src/assets/images/member/member2.png',
        text: '2. 이번 시크리또 프로젝트 BE 역할을 맡은 이승우 입니다 '
    },
    {
        img: 'src/assets/images/member/member3.png',
        text: '3. 이번 시크리또 프로젝트 BE 역할을 맡은 이승우 입니다'
    },
    {
        img: 'src/assets/images/member/member4.jpg',
        text: '4. 이번 시크리또 프로젝트 FE 리더 지인성 입니다.'
    },
    {
        img: 'src/assets/images/member/member5.jpg',
        text: '5. 이번 시크리또 프로젝트 FE 역할을 맡은 김현창 입니다 '
    },
    {
        img: 'src/assets/images/member/member6.png',
        text: '6. 이번 시크리또 프로젝트 FE 역할을 맡은 신시원 입니다'
    }
])

const user = ref<Array<Guide>>([
    {
        img: 'src/assets/images/guides/master1.png',
        text: '1. 메인화면 방리스트 조회 항목에서 초대코드입력을 누르고 Enter를 눌러 방에 입장할 수 있어요'
    },
    {
        img: 'src/assets/images/guides/user1.png',
        text: '2. 방에서 쓸 닉네임을 지정해주세요'
    },
    {
        img: 'src/assets/images/guides/user2.png',
        text: '3. 방장이 승인을 해주면 방에 들어갈 수 있어요'
    },
    {
        img: 'src/assets/images/guides/user3.png',
        text: '4. 입장이 승인되고 게임이 시작되면 참여인원들이 보이는 페이지로 이동합니다'
    },
    {
        img: 'src/assets/images/guides/user3.png',
        text: '4. 입장이 승인되고 게임이 시작되면 참여인원들이 보이는 페이지로 이동합니다'
    },
    {
        img: 'src/assets/images/guides/user4.png',
        text: '5. 게임 시작후 참여인원중 나의 마니띠는 파란색 버튼으로 표시가 됩니다.'
    },
    {
        img: 'src/assets/images/guides/user4.png',
        text: '6. 마니또라고 생각드는 사람이 있나면 마니또 예측하기 기능을 활용해보세요'
    },
    {
        img: 'src/assets/images/guides/user5.png',
        text: '7. 예측 사유를 적어주시면 추후 타임라인으로 제공이 됩니다'
    },
    {
        img: 'src/assets/images/guides/user6.png',
        text: '8. 내 미션 탭에서는 나에게 주어진 미션을 확인할 수 있어요.'
    },
    {
        img: 'src/assets/images/guides/user11.png',
        text: '9. 미션을 수행하셨나요? 인증게시판을 통해 미션을 인증해보세요'
    },
    {
        img: 'src/assets/images/guides/user8.png',
        text: '10. 마니또로부터 선물을 받으셨나요? 자랑게시판을 통해 자랑해보세요'
    },
    {
        img: 'src/assets/images/guides/user7.png',
        text: '11. 채팅 메뉴를 이용하시면 마니또, 마니띠와 익명으로 채팅하거나 단체채팅도 할 수 있어요'
    },
    {
        img: 'src/assets/images/guides/user9.png',
        text: '12. 게임이 종료되면 게임 통계와 타임라인을 확인해보세요'
    },
    {
        img: 'src/assets/images/guides/user10.png',
        text: '13. 후기를 남겨보세요, 실시간으로 사람들의 반응을 볼 수 있어요'
    }
])

const showData = ref<Array<Guide>>([
    {
        img: '',
        text: ''
    }
])

const leftHandler = () => {
    if (showIndex.value !== 0) showIndex.value--
}

const rightHandler = () => {
    if (showIndex.value !== showData.value.length - 1) showIndex.value++
}

const fetchData = async (data: Array<Guide>) => {
    showData.value = data
    showIndex.value = 0
}

const Secreto = async () => {
    await fetchData(secreto.value)
}

const Setting = async () => {
    await fetchData(setting.value)
}

const User = async () => {
    await fetchData(user.value)
}

onMounted(() => {
    Setting()
})
</script>

<template>
    <div class="flex flex-1 flex-col gap-[30px]">
        <div class="flex flex-col items-center md:mx-[20%] md:gap-[30px] max-md:gap-[15px]">
            <HeaderLogo />
            <TextAtom custom-class="md:text-1 max-md:text-2">시크리또를 소개합니다</TextAtom>
            <TextAtom custom-class="md:text-2 max-md:text-4 text-center"
                >우리 서비스 시크리또는 싸피 10기 8반 전공자 4명과 비전공자 2명 총 6명의 팀원이 함께
                모여 만들었습니다. 모두가 친해지는 그날까지 시크리또가 함께 하겠습니다.
            </TextAtom>
        </div>
        <div class="flex flex-1 flex-col">
            <div class="flex flex-col w-full items-center justify-evenly">
                <TextAtom custom-class="md:text-1 max-md:text-2">서비스 사용법</TextAtom>
                <div class="flex w-full justify-evenly">
                    <div class="flex flex-col items-center cursor-pointer" @click="Secreto">
                        <CharacterAtom custom-class="w-[60px]" /><TextAtom>시크리또 멤버</TextAtom>
                    </div>
                    <div class="flex flex-col items-center cursor-pointer" @click="Setting">
                        <HomeFilled style="font-size: 40px" /><text>방장 가이드</text>
                    </div>
                    <div class="flex flex-col items-center cursor-pointer" @click="User">
                        <UserOutlined style="font-size: 40px" /><text>유저 가이드</text>
                    </div>
                </div>
            </div>
            <div class="flex flex-1 justify-center">
                <div
                    class="flex flex-col md:min-w-[1200px] md:p-[20px] md:gap-[20px] md:h-[600px] max-md:min-h-[400px]"
                >
                    <div class="flex flex-[4] max-md:h-[320px] justify-center items-center">
                        <LeftOutlined
                            style="font-size: 24px; cursor: pointer"
                            class="max-md:absolute left-0"
                            @click="leftHandler"
                        />
                        <img :src="showData[showIndex].img" class="h-full object-contain" />
                        <RightOutlined
                            style="font-size: 24px; cursor: pointer; right: 0"
                            class="max-md:absolute right-0"
                            @click="rightHandler"
                        />
                    </div>
                    <div
                        class="flex h-[40px] max-md:h-[80px] bg-A805LightGrey justify-center items-center md:text-3 md:text-center max-md:text-4 text-bold"
                    >
                        <TextAtom>{{ showData[showIndex].text }}</TextAtom>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped></style>
