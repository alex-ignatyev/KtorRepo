package com.biologyapp_backend.db

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database

object DatabaseFactory {

    fun init() {
        val config = configureHikari()
        val dataSource = HikariDataSource(config)
        Database.connect(dataSource)
    }

    private fun configureHikari(): HikariConfig {
        val config = HikariConfig()
        config.driverClassName = "org.postgresql.Driver"
        val host = "ec2-176-34-211-0.eu-west-1.compute.amazonaws.com"
        val port = "5432"
        val database = "dfjvd3f1goqbf2"
        val user = "wikojzomofvssc"
        val password = "56fe1b8bdd831a6ba037eeb594ede5f91db734a0b676195c23afbdec5fe4f552"
        config.jdbcUrl =
            "jdbc:postgresql://$host:$port/$database?user=$user&password=$password"
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()
        return config
    }
}
