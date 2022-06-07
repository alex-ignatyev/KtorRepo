package com.biologyapp_backend.db.facade

import com.biologyapp_backend.db.model.User
import com.biologyapp_backend.db.table.Users
import com.biologyapp_backend.utils.dbQuery
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

class DAOFacadeImpl : DAOFacade {
    private fun resultRowToUser(row: ResultRow) = User(
        login = row[Users.login],
        email = row[Users.email],
        password = row[Users.password],
        token = row[Users.token],
        firstName = row[Users.firstName],
        secondName = row[Users.secondName],
        birthday = row[Users.birthday],
        phone = row[Users.phone],
        clas = row[Users.clas]
    )

    override suspend fun addUser(user: User): Unit = dbQuery {
        val insertStatement = Users.insert {
            it[login] = user.login
            it[email] = user.email
            it[password] = user.password
            it[token] = user.token
            it[firstName] = user.firstName
            it[secondName] = user.secondName
            it[birthday] = user.birthday
            it[phone] = user.phone
            it[clas] = user.clas
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToUser)
    }

    override suspend fun getAllUsers() = dbQuery {
        Users.selectAll().map(::resultRowToUser)
    }
}

interface DAOFacade {
    suspend fun addUser(user: User)
    suspend fun getAllUsers(): List<User>
}