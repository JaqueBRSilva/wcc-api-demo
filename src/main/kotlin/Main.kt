import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*

fun main(){

    val userDao = UserDao()

    val app = Javalin.create().apply {
        exception(Exception::class.java) { e, ctx -> e.printStackTrace()}
        error(404) { ctx -> ctx.json("NOT FOUND")}
    }.start(7000)

    app.routes {

        // MOSTRANDO TODOS OS USUÁRIOS
        get("/users") { ctx ->
            ctx.json(userDao.users)
        }

        // BUSCANDO O USUÁRIO PELO id
        get("/users/{user-id}") { ctx ->
            ctx.json(userDao.findById(ctx.pathParam("user-id").toInt())!!)
        }
    }
}