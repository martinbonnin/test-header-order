import com.apollographql.apollo3.mockserver.MockResponse
import com.apollographql.apollo3.mockserver.MockServer
import io.ktor.client.*
import io.ktor.client.engine.js.*
import io.ktor.client.request.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class MainTest {
    @Test
    fun testStuff() = runTest {
        val mockServer = MockServer()
        mockServer.enqueue(
            MockResponse.Builder()
                .body("Hello")
                .statusCode(200)
                .addHeader("Set-Cookie", "Key1=Value1")
                .addHeader("Set-Cookie", "Key2=Value2")
                .build()
        )

        val response = HttpClient(Js) {
            expectSuccess = false
        }.get(mockServer.url())

        val cookies = mutableListOf<String>()
        response.headers.forEach { s, strings ->
            println("$s $strings")
            if (s == "set-cookie") {
                cookies.add(strings.joinToString())
            }
        }
        assertEquals(2, cookies.size)
        mockServer.close()
    }
}