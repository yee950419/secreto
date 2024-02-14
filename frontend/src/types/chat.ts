export interface Message {
    message?: string
    profileUrl?: string
    chatNo? : number
    readYn? : boolean
    sendAt? : string
    sender? : string
    senderId? : number
    type?: string
}
export interface ChatRoomType {
    name: string
    imageUrl: string
}

export interface chatInfo {
    chatNo : number,
    chatUserNo : number,
    chattingUserType : string,
    roomUserNo : number,
}
