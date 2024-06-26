/** @type {import('tailwindcss').Config} */
export default {
    content: ['./index.html', './src/**/*.{vue,js,ts,jsx,tsx}'],
    theme: {
        extend: {
            height: {
                'real-screen': 'calc(var(--vh) * 100)'
            },
            colors: {
                A805Black: '#292929',
                A805White: '#F7F7F7',
                A805Red: '#FF2323',
                A805Purple: '#756AB6',
                A805Cream: '#FFE5E5',
                A805Claret: '#E0AED0',
                A805Violet: '#AC87C5',
                A805Green: '#01D04C',
                A805Khaki: '#3D4A3D',
                A805Grey: '#bbbbbb',
                A805Blue: '#0085FF',
                A805LightBlue: '#00B8FF',
                A805DarkGrey: '#858585',
                A805LightGrey: '#EEEEEE',
                A805RealWhite: '#FFFFFF',
                A805Neutral: '#EAEEF3'
            }
        },
        minHeight: {
            'real-screen': 'calc(var(--vh) * 100)'
        },
        fontFamily: {
            Iceland: ['Iceland'],
            Prentedard: ['Prentedard']
        }
    },
    plugins: []
}
