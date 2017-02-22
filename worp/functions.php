<?php
/**
 * Created by PhpStorm.
 * User: usuario
 * Date: 8/02/17
 * Time: 10:52
 */

include (TEMPLATEPATH.'/includes/scripts.php');

?>

<?php


function load_scripts(){

    wp_enqueue_style('materialize', get_template_directory().'/css/materialize.css');
    wp_enqueue_script('materialize_js', get_template_directory().'/js/materialize.js');
}

 add_action('wp_enqueue', 'load_scripts');

?>
