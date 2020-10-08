package data.network


import app.Config
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MyApi {


    @FormUrlEncoded
    @POST("auth/login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<ResponseBody>

    @FormUrlEncoded
    @POST("auth/register")
    fun register(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("name") name: String,
        @Field("type") type: String
    ): Call<ResponseBody>



//    @FormUrlEncoded
//    @POST("auth/login")
//    fun login2(
//       @Body user: User
//    ): Call<ResponseBody>


    companion object{
        operator fun invoke(): MyApi{
            return Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}