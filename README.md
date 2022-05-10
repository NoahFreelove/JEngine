# JEngine
A Simple 2D JavaFX Based Game Engine

## Example
Check out `src/main/java/com/Examples/MovingSquare/` to see a working example of a simple game with 2 moving squares with physics.

## Quick Start

Firstly! If it isn't already, add the following to your module-info.java file:
`requires javafx.controls;`

`requires javafx.fxml;`

`requires java.desktop;`

### Create your Scene, Window, and Camera
In a new JavaFX project, go to the `start(Stage stage)` method.

Now write the following code:

`GameScene scene = new GameScene(5, "My First Scene");` 
* 5 - The initial size of the scene (in GameObjects)
* "My First Scene" - The name of the scene

Now that you've created your scene, create the game window:

`GameWindow window = new GameWindow(scene, 1f, "My First Window", stage);`
* scene, The scene you just created, will render it by default
* 1f - The initial scale of the window (based on 1280x720)
* "My First Window" - The name of the window. Will show up in the window bar
* stage - The stage the JavaFX method `start(Stage stage)` provided you.

Now that you've created your window, you need to create a camera, so you can render the scene:

`GameCamera camera = new GameCamera(new Vector3(0,0,0), window, scene, null, new Identity("MainCamera", "camera"));`
* new Vector3(0,0,0) - The initial position of the camera
* window - The window you just created
* scene - The scene you just created
* null - The object the camera should focus on, we can leave it null for now
* new Identity("MainCamera", "camera") - The name and tag of the camera. Lets us uniquely identify it.

Don't forget to add the camera to the scene!
`scene.add(camera);`

## Starting the game
To start your window, write the following code:

`window.setTargetFPS(60);` Set the target FPS of the window. 60 recommended.

`window.setBackgroundColor(Color.BLACK);` Set the background color of the window.

`window.start();` Start the window.

## Adding GameObjects
Right now if you start your window, you should see a black screen... Let's change that.

*GameObjects are objects that can be added to the scene. They can be anything from a static sprite to a moving player.*

Check out the MovingSquare example to see how to create a moving player, right now we'll just create a static sprite.

### Create your image

`GameImage image = new GameImage("your image path here", 64 ,64);`
* "your image path here" - The path to the image you want to use
* 64 - The width of the image
* 64 - The height of the image

### Create your transform
`Transform transform = new Transform(new Vector3(0,0,0), new Vector3(0,0,0), new Vector3(1,1,1));`
* new Vector3(0,0,0) - The initial position of the image
* new Vector3(0,0,0) - The initial rotation of the image
* new Vector3(1,1,1) - The initial scale of the image

### Create the Object
`Sprite gameObject = new Sprite(transform, image, new Identity("name", "tag"));`

### Add the Object to the Scene
`scene.add(gameObject);`

Now if you run your window, you should see your Sprite at the top Left of the screen.

JavaFX left/right coords work like so:

(0,0) is the top left corner

(1280,720) is the bottom right corner

(0,720) is the bottom left corner

(1280,0) is the top right corner

## Congrats, you created your first JEngine scene.
