package com.biologyapp_backend.db

import com.biologyapp_backend.utils.dbQuery
import com.biologyapp_backend.utils.generateUUID
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import java.util.Random
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

object DatabaseFactory {

    fun init() {
        val config = configureHikari()
        val dataSource = HikariDataSource(config)
        Database.connect(dataSource)
    }

    private fun configureHikari(): HikariConfig {
        val config = HikariConfig()
        config.driverClassName = "org.postgresql.Driver"
        val host = "ec2-34-198-186-145.compute-1.amazonaws.com"
        val port = "5432"
        val dbName = "dcbrj39ktdkb2k"
        val userName = "setoeljavyrthl"
        val password = "0e1feb3b164b855930f00c16605c391ee81caedc1bc3e546e96fca8cb4f5e7cf"
        config.jdbcUrl =
            "jdbc:postgresql://$host:$port/$dbName?user=$userName&password=$password"
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()
        return config
    }
}

@Serializable
data class User(val id: Int?, val login: String)

object Users : Table() {
    val id = integer("id").autoIncrement()
    val login = varchar("login", 12)

    override val primaryKey = PrimaryKey(id)
}

interface DAOFacade {
    suspend fun addUser(login: String): String
    suspend fun getAllUsers(): List<User>
}

class DAOFacadeImpl : DAOFacade {
    private fun resultRowToArticle(row: ResultRow) = User(
        id = row[Users.id],
        login = row[Users.login],
    )

    override suspend fun addUser(login: String): String = dbQuery {
        val insertStatement = Users.insert {
            it[id] = Random().nextInt()
            it[Users.login] = login
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToArticle)
        return@dbQuery generateUUID()
    }

    override suspend fun getAllUsers() = dbQuery {
        Users.selectAll().map(::resultRowToArticle)
    }
}
