package com.fdb.what_to_eat

import com.fdb.what_to_eat.domain.Category
import com.fdb.what_to_eat.domain.MealType
import com.fdb.what_to_eat.domain.Menu
import com.fdb.what_to_eat.repository.CategoryRepository
import com.fdb.what_to_eat.repository.MealTypeRepository
import com.fdb.what_to_eat.repository.MenuRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class DataInitializer(
    private val categoryRepository: CategoryRepository,
    private val mealTypeRepository: MealTypeRepository,
    private val menuRepository: MenuRepository
) : ApplicationRunner {

    @Transactional
    override fun run(args: ApplicationArguments) {
        val categories = if (categoryRepository.count() == 0L) {
            listOf("한식", "중식", "일식", "양식", "분식").map {
                categoryRepository.save(Category(name = it))
            }
        } else {
            categoryRepository.findAll()
        }

        val mealTypes = if (mealTypeRepository.count() == 0L) {
            listOf("아침", "점심", "저녁", "간식").map {
                mealTypeRepository.save(MealType(name = it))
            }
        } else {
            mealTypeRepository.findAll()
        }

        if (menuRepository.count() == 0L) {
            val menuData = mapOf(
                "아침" to mapOf(
                    "한식" to listOf("미역국", "북어국", "콩나물국밥", "전복죽", "야채죽"),
                    "중식" to listOf("계란토마토볶음", "완당", "죽", "만두", "꽃빵"),
                    "일식" to listOf("낫또밥", "미소시루", "생선구이정식", "계란말이", "오니기리"),
                    "양식" to listOf("스크램블에그", "팬케이크", "프렌치토스트", "시리얼", "에그베네딕트"),
                    "분식" to listOf("주먹밥", "김밥", "라면", "토스트", "우동")
                ),
                "점심" to mapOf(
                    "한식" to listOf("김치찌개", "된장찌개", "비빔밥", "불고기덮밥", "제육볶음"),
                    "중식" to listOf("짜장면", "짬뽕", "볶음밥", "잡채밥", "마파두부덮밥"),
                    "일식" to listOf("돈카츠", "규동", "사케동", "라멘", "초밥"),
                    "양식" to listOf("파스타", "함박스테이크", "샌드위치", "샐러드", "피자"),
                    "분식" to listOf("떡볶이", "순대", "튀김", "쫄면", "잔치국수")
                ),
                "저녁" to mapOf(
                    "한식" to listOf("삼겹살", "갈비찜", "닭볶음탕", "보쌈", "아구찜"),
                    "중식" to listOf("탕수육", "깐풍기", "양장피", "유산슬", "훠궈"),
                    "일식" to listOf("회덮밥", "스키야키", "야키토리", "텐동", "나베"),
                    "양식" to listOf("스테이크", "리조또", "라스냐", "감바스", "와인플래터"),
                    "분식" to listOf("모듬분식", "비빔만두", "김떡순", "칼국수", "수제비")
                ),
                "간식" to mapOf(
                    "한식" to listOf("떡볶이", "호떡", "붕어빵", "식혜", "수정과"),
                    "중식" to listOf("군만두", "춘권", "버블티", "포춘쿠키", "지마구"),
                    "일식" to listOf("타코야키", "모찌", "푸딩", "당고", "말차아이스크림"),
                    "양식" to listOf("조각케이크", "쿠키", "머핀", "도넛", "브라우니"),
                    "분식" to listOf("핫도그", "소떡소떡", "회오리감자", "닭강정", "츄러스")
                )
            )

            for (mealType in mealTypes) {
                val mealName = mealType.name
                val categoryMap = menuData[mealName] ?: continue
                
                for (category in categories) {
                    val categoryName = category.name
                    val menus = categoryMap[categoryName] ?: continue
                    
                    menus.forEach { menuName ->
                        menuRepository.save(Menu(name = menuName, category = category, mealType = mealType))
                    }
                }
            }
        }
    }
}
