<?php
/**
 * Created by PhpStorm.
 * User: usuario
 * Date: 8/02/17
 * Time: 12:00
 */

?>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" <?php language_attributes(); ?>>
<head profile="http://gmpg.org/xfn/11">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>

        <?php

            wp_title( '|' , true , 'right' );
            blog_info( 'name' );

        ?>

    </title>

    <?php wp_head() ?>

</head>

<body  >

    <div class="container">

        <div class="row">

            <div id="header">

                <h1><a href="<php esc_url( home_url( '/' ) ); ?>" rel="home"><?php blog_info( 'name' ) ?></a></h1>


            </div>

        </div>

    </div>
    <div class="row">
</body>

 <?php


 ?>
