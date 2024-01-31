// const eventSource = new EventSource(import.meta.env.VITE_API_BASE_URL + '/sse')

// // 서버로부터 알림 메시지가 오면 적절한 처리 로직을 수행
// eventSource.addEventListener('alarm', (event) => {
//     console.log('새로운 알림이 도착했습니다', event.data)
// })

// // 서버로부터 채팅 메시지가 왔다는 메시지를 받으면 적절한 처리 로직을 수행
// eventSource.addEventListener('chat', (event) => {
//     console.log('새로운 메시지가 도착했습니다.', event.data)
// })

// eventSource.addEventListener('error', (event) => {
//     console.error('Server Sent Event error:', event)
// })
