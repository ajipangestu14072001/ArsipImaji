package arsip.imaji.id.helper

import arsip.imaji.id.model.Person
import com.google.firebase.database.DatabaseReference

object Constant {
    const val clientID = "148438306885-8vv8vue5p5refh1ndjci8jsgr01650qf.apps.googleusercontent.com"
    private val personNames = arrayOf(
        "Aliffiyah Z.",
        "Halimatus",
        "Mila Nurviana",
        "Niken Syantik",
        "Rossi Amanda",
        "Yeni Ulfah",
        "Tami Ade Sanita")

    private val personImages = arrayOf(
        "https://firebasestorage.googleapis.com/v0/b/arsipimaji-2b711.appspot.com/o/Aliffiyah%20Z.jpg?alt=media&token=a13d38f1-8ec1-4812-b34f-2d7424cb734c",
        "https://firebasestorage.googleapis.com/v0/b/arsipimaji-2b711.appspot.com/o/Halimatus.jpg?alt=media&token=2bf70916-b136-4fe3-8a61-7e492b7a07bf",
        "https://firebasestorage.googleapis.com/v0/b/arsipimaji-2b711.appspot.com/o/Mila%20Nurviana.jpg?alt=media&token=e503a92a-5044-4187-accf-b1c472c8af0f",
        "https://firebasestorage.googleapis.com/v0/b/arsipimaji-2b711.appspot.com/o/niken%20syantik%20_v.JPG?alt=media&token=0fb75a17-270d-4854-aad6-b6f7d6ebc3b0",
        "https://firebasestorage.googleapis.com/v0/b/arsipimaji-2b711.appspot.com/o/Rossi%20Amanda%20(2).jpg?alt=media&token=c7042c16-64f5-4e24-8a01-a42a9002025e",
        "https://firebasestorage.googleapis.com/v0/b/arsipimaji-2b711.appspot.com/o/Yeni%20Ulfah.jpg?alt=media&token=b73c3376-7e22-4ffa-b8d6-325fd657db0a",
        "https://firebasestorage.googleapis.com/v0/b/arsipimaji-2b711.appspot.com/o/Tami%20Ade%20Sanita_.jpg?alt=media&token=fe9f8883-9169-4de3-87f8-e03070c7c1ef")

    private val personDetails = arrayOf(
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book",
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book",
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book",
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book",
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book",
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book",
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book",
    )

    val listData: ArrayList<Person>
        get() {
            val list = arrayListOf<Person>()
            for (position in personNames.indices) {
                val foods = Person()
                foods.name = personNames[position]
                foods.detail = personDetails[position]
                foods.photo = personImages[position]
                list.add(foods)
            }
            return list
        }

    var databaseReference: DatabaseReference? = null
}