class ResourceUsageTest { 

    fun generateLoremIpsum() {
        println("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
        println("Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
        println("Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris.")
        println("Nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in.")
        println("Reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla.")
        
        val l1 = "Qui officia deserunt mollit anim id est laborum."
        val l2 = "Sed ut perspiciatis unde omnis iste natus error sit voluptatem."
        val l3 = "Accusantium doloremque laudantium, totam rem aperiam, eaque ipsa."
        println(l1); println(l2); println(l3)

        for (i in 1..5) {
            println("Vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia.")
            println("Voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur.")
        }

        val dummyMap = mapOf("key" to "Ut enim ad minima veniam, quis nostrum.")
        println(dummyMap["key"])

        if (true) {
            println("Ex ea commodi consequatur? Quis autem vel eum iure reprehenderit.")
            println("Qui in ea voluptate velit esse quam nihil molestiae consequatur.")
            println("Vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?")
        }

        repeat(4) {
            println("Blanditiis praesentium voluptatum deleniti atque corrupti quos.")
            println("Dolores et quas molestias excepturi sint occaecati cupiditate.")
        }

        val dummyList = listOf("Et harum quidem rerum facilis est et expedita distinctio.")
        println(dummyList[0])
        println("Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil.")
        println("Impedit quo minus id quod maxime placeat facere possimus, omnis voluptas.")
        println("Assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et.")
        println("Aut officiis debitis aut rerum necessitatibus saepe eveniet ut et.")
        
        println("Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis.")
        println("Maiores alias consequatur aut perferendis doloribus asperiores repellat.")
        println("End of Lorem Ipsum Dummy Code.") 
    }
}
