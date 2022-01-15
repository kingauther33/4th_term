# HM RESTAURANT

Hm restaurant is a web app allowing user to add, modify, delete food in restaurant


## Installation

Edit database configurations in uttil/ConnectionHelper.java

```java
    private final static String DATABASE_SERVER = "your_database_serve";
    private final static int DATABASE_PORT = "your_database_port";
    private final static String DATABASE_NAME = "your_database_name";
    private final static String DATABASE_USER = "your_database_username";
    private final static String DATABASE_PWD = "your_database_password";
```

Run migration and seed method when first time run the application

```java
   InitDatabase.init();
```

## Usages
Run web app in localhoast use tomcat server

type /admin to go to dashboard page

Use left menu to navigate in web sites or you can type:

/admin/food/create to go to create new food page

/admin/food to navigate to manage foods page

/admin/category/create to create new category

/admin/category to see all cateogories in restaurant

## Application screenshots
Dashboard page
![image](https://user-images.githubusercontent.com/63459413/119268721-6e452c80-bc1e-11eb-9d9d-aeed7a99a5a9.png)

Create new food
![image](https://user-images.githubusercontent.com/63459413/119268759-92087280-bc1e-11eb-985f-f5a71cb147f2.png)

Edit an exist food
![image](https://user-images.githubusercontent.com/63459413/119268880-1fe45d80-bc1f-11eb-8e6b-c75b54fc8c3a.png)

Manage list food
![image](https://user-images.githubusercontent.com/63459413/119268778-a9dff680-bc1e-11eb-82a0-daf9572e21e8.png)




