class ResourceUsageTest { 

    fun test() {
        println("test") 
    }

    fun menu(num : Int){
        when {
            num == 1 -> println("아이스 아메리카노")
            num == 2 -> println("아이스 바닐라 라떼에 버블 추가")
            num == 3 -> println("따뜻한 카라멜마끼아또") 
            num > 3 -> println("딸기 바나나 주스")
        }
    }
    
    fun mockMenu(num : Int){
        when {
            num == 1 -> println("가짜 아메리카노")
            num == 2 -> println("가짜 라떼")
            num == 3 -> println("가짜 카라멜마끼아또") 
            num > 3 -> println("가짜 바나나 주스")
        }
    }
}
