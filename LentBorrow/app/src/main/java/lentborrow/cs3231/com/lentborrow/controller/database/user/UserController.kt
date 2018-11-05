package lentborrow.cs3231.com.lentborrow.controller.database.user

import lentborrow.cs3231.com.lentborrow.controller.database.DatabaseController

class UserController(): DatabaseController(){
    fun createNewUser(user:User):User{
        val userID = this.pushObject("User",user.getDatabaseForm());
        user.userID = userID;
        return user;
    }
}