class ResourceUsageTest { 

    fun test() {
        println("test") 
    }

    fun coffee(ice : Int){
        when {
            ice == 1 -> println("얼음 하나")
            ice == 2 -> println("얼음 둘")
            ice == 3 -> println("얼음 셋") 
            ice > 3 -> println("얼음 많이")
        }
    }
}