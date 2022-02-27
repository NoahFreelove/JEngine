# JEngine
A simple 2D JavaFX based game engine

## Quick-Start
1. Create a JavaFX project and import the engine.
2. Set your engine preferences with `JEngine.Utility.Settings.EnginePrefs`
3. Create a new Window in the start method by using: `JWindow window = new JWindow(800,800,"Window Name", stage);`
4. Create a new Scene (the thing that holds all your Objects) by using: `JScene scene = new JScene(window, max objects 
in your scene, "Scene 1")`
5. Now create a camera using: `JCamera camera = new JCamera(Vector3(0,0,0), window, scene, null, 800,
new JIdentity("Camera1", "cameras"))`
6. To access your current Window,Scene, and main camera anywhere, use JEngine.Game.Visual.JSceneManager: 
`JSceneManager.init(window, scene, camera)`

* `Vector3`: Initial position `(x,y,z)`
* `window`: `JWindow` you created in step 3
* `scene`: `JScene` you created in step 4
* `null`: `JObject`, the camera's parent. I kept it null because we don't want it moving or anything right now.
* `800`: The camera's `FOV`. Extends `x` pixels right and down. I set it to 800, so it covers the entire size of the window
* `JIdentity`: The camera's unique identity. `First String is its name`, `The Second String is its tag`.
7. Set the main camera with: `window.setCamera(camera)`
8. Add the camera to the scene: `scene.add(camera)`
9. Set the window's target FPS: `window.setTargetFPS(30)`
10. Start the window: `window.start()`

Congrats! You have a window updating 30 times a second that renders any object you add to the scene.

Let's add a simple sprite to the scene.

## Creating Objects
Creating scene objects in JEngine is relatively easy. Everything is derived from a JObject which can be added to any scene.
1. Choose the type of object you want to create. I'll create a Sprite (a simple 2D Sprite)
2. Create it! `Sprite sprite = new Sprite(new Transform(new Vector3(336,336,0), new Vector3(0,0,0), new Vector3(1,1,1)),
new JImage(true, "filePathToSprite", 128,128), new JIdentity("MySprite", "sprite"))`

That was a lot so here's an explanation: 
* `Transform` consists of 3, `Vector3`'s. Position, Rotation, and Scale.
* `JImage` is JEngine's Image class. It takes a `boolean initial active state`, a `String filepath` (to the image),
a `int sizeX` and `int sizeY`
* `JIdentity` is JEngine's way of _semi_-unique identification. Give your objects a unique name and tag! 

It will allow you to pick out specific objects in a JScene.
3. Add it to the scene with: `scene.Add(JObject)`

Replace `JObject` with the object you created. In this case you would do `scene.Add(sprite)`
4. Run your project and see the sprite in action!