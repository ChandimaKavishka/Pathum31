<?php

    //database connection
    $host = "localhost";
    $user = "root";
    $password = "";
    $db = "student";

    $conn = mysqli_connect($host, $user, $password, $db);

    if(mysqli_connect_error()) {
        echo mysqli_connect_error();
    }else{
        echo "connected";
    }

    // $message = "helloworld";

    //register student user

    if(isset($_POST["submit"])){
        
        //echo "connect";

        $name = $_POST["name"];
        $email = $_POST["email"];
        $password = $_POST["password"];
        $c_pass = $_POST["confirm_password"];

        if($name==null || $email==null || $password==null){
            
            $message = "all field is required";

        }else if($password != $c_pass){
            $message = "no match";
        
        }else{

            //add data to database

    
            $query = "INSERT INTO `user` VALUES ('','$name','$email','$password')";

            $runquery = mysqli_query($conn, $query);

            
            if ($runquery) {
                $message = "successfully";
            }

        }

        
    }


    //delete table data
    if (isset($_POST["delete"])) {

        $id_for_delete = $_POST["delete_id"];


        $deletequery = "DELETE FROM `user` WHERE id='$id_for_delete'";

        $run_deletequery = $conn->query($deletequery);
        
        if ($run_deletequery) {
            $message = "successfully deleted";
        }
    }

    //update table data
    if (isset($_POST["update"])) {

        $id = $_POST["id"];
        $name = $_POST["name"];
        $email = $_POST["email"];
        $password = $_POST["password"];
       

        $update_query = "UPDATE `user` SET `id`='$id',`name`='$name',`email`='$email',`password`='$password' WHERE id='$id'";

        $run_update_query = mysqli_query($conn, $update_query);

        if ($run_update_query) {
            $message = "successfully updated!!";
        }

    }


?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <!-- <h1>Hellow</h1> -->

    <!-- Display the message -->
    <?php
    if (!empty($message)) {
        echo "<p>$message</p>";
    }
   ?>


    <form action="index.php" method="post">
        <input type="text" name="name" placeholder="name"><br>
        <input type="text" name="email" placeholder="email"><br>
        <input type="password"  name="password" placeholder="password"><br>
        <input type="text"  name="confirm_password" placeholder="confirm password"><br>
        <input type="submit" name="submit" value="Submit">
    </form>

    <table border="1">
                <thead>
                    <tr>
                        <th>id</th>
                        <th>name</th>
                        <th>email</th>
                        <th>password</th>
                        <th>action</th>
                    </tr>
                </thead>

               <?php 
                    //view data from database

                    $sql = "SELECT * FROM `user`";

                    $query2 = $conn->query($sql);

                    while($row = $query2->fetch_assoc()){
                ?>

                <tbody>
                    <tr>
                        <td><?php echo $row['id'] ?></td>
                        <td><?php echo $row['name'] ?></td>
                        <td><?php echo $row['email'] ?></td>
                        <td><?php echo $row['password'] ?></td>
                        <td>
                            <!-- for delete record -->

                            <form action="index.php" method="post">
                                <input type="hidden" name="delete_id" value="<?php echo $row['id'] ?>">
                                <input type="submit" name="delete" value="Delete">
                            </form>

                        </td>
                    </tr>
                </tbody>      
                        
                <?php
                    }
                ?>

    </table>

    <br><br><br>
    <h2>update data</h2>
    <form action="index.php" method="post">
        <input type="text" name="id" placeholder="id"><br>
        <input type="text" name="name" placeholder="name"><br>
        <input type="text" name="email" placeholder="email"><br>
        <input type="password"  name="password" placeholder="password"><br>
        <input type="submit" name="update" value="update">
    </form>

</body> 
</html>