
# CaseStudy_Aug29
PerScholas Java Case Study
# User Listing page:
![usersListing1](https://user-images.githubusercontent.com/56694905/131401001-925f74e3-0937-4048-af11-150efafffdd1.PNG)
# Create New User:
![createNewUser1](https://user-images.githubusercontent.com/56694905/131401061-ea495609-05a4-4ddd-baf4-f0d3c20c8c7c.PNG)


This Case Study is User Management System, which is also part one of a more complex E-commerce Shop project, that is why you see the package is called Shop.
---------------------------------------------------------------------------------
Project structure:
  ShopEcommerce******* 
      *ShopCommom: common library(Entities, roles and users);
      *ShopWebParent:
            *ShopBackEnd(controllers and service and repositories and tests, most files are here, also path of created .log under target folder. );
            *ShopFrontEnd.
            
            #//Part 1: User management system.

Step 1: Create a maven project as ShopProject (root project)

        step 2: Create Spring boot starter project as ShopCommon 
                (common library, use jpa dependency injection,delete application file under /main/java, delete test package, add module to root pom.xml)
                
                Step 3: Create Spring boot starter project as ShopWebParent project
                        (delete UmsWebParentApplication under /main/java/, delete test package, add module to root pom.xml,
                         add all needed dependency here(jpa,thymeleaf,mysql,test, webjars......)
                        go to project properties,go to Project Natures, remove Java Maven Nature.)
                        
                        Step 4: Create Spring boot starter project as child: ShopBackEnd project 
                                (Delete UmsBackEndApplicationTests.java, config application.properties)
                                 (UMSBackEnd: refactor /com.shop to /com.shop.admin, create a MainController.java, 
                                        create a template folder under resources, 
                                        create a index.html, run backend application)

                                
                        Step 5: Create Spring boot starter project as child: ShopFrontEnd (refactor /com.shop to /com.shop.site, config application.properties)
                                (create template folderand index.html, run frontent application)

Step 6:

Building User model
        -Create User Entity and establish connection with SQL WorkBench to create Tables
        -Create User Service
        -Create User Repository
        -Create User Controller

             ---------------------------------------------------------------------------------                         
user uploaded photo is under ShopBackEnd, target folder.
# Project Main Structure         
![projectDir1](https://user-images.githubusercontent.com/56694905/130331238-dab03330-1c99-4ebb-9d27-378023f69967.PNG)

# Project BackEnd Structure
![backendDir1](https://user-images.githubusercontent.com/56694905/130331234-b5fbe246-e229-44a3-bf46-cd630a2bf362.PNG)

# Project Backend Structure 2
![backendDir2](https://user-images.githubusercontent.com/56694905/130331239-a7479dc5-bd26-4777-bf7f-f1073b59909d.PNG)

# User authorization
# ADD 404/403/500 Error page handling.
                          
