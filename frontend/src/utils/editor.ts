import ImageUploader from 'quill-image-uploader'
import { imageUpload } from '@/api/upload'

export const toolbarOptions = [
    ['bold', 'italic', 'underline', 'strike'], // toggled buttons
    [{ header: [1, 2, 3, 4, 5, 6, false] }],
    [{ color: [] }, { background: [] }], // dropdown with defaults from theme
    [{ font: [] }],
    [{ align: [] }],
    ['link', 'image'],
    [{ list: 'ordered' }, { list: 'bullet' }],
    [{ indent: '-1' }, { indent: '+1' }] // outdent/indent
]

export const modules = {
    name: 'imageUploader',
    module: ImageUploader,
    options: {
        upload: (file: Blob) => {
            return new Promise((resolve, reject) => {
                const formData = new FormData()
                formData.append('file', file)
                imageUpload(
                    formData,
                    (response) => {
                        const data = response.data
                        if (data.status === 'OK') {
                            const url = data.result.saveFile
                            resolve(url)
                        }
                        console.log(response)
                    },
                    (error) => {
                        reject('Upload failed')
                        console.error(error)
                    }
                )
            })
        }
    }
}
