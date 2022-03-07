# JEngine
A Simple 2D JavaFX Based Game Engine

## Example
Check out `src/main/java/com/basicexample` to see a working example of a scene with a movable player and text

##JEngine Structure
JEngine's structure may be similar to other game engine's you've used.

JEngine uses `JObjects` as a base class for almost everything in the engine. Every `JObject`, when added to the scene,
has its own overridable `Update()` and `Start()` methods.

A `JWindow` is a window that displays the active JScene. **You should always only ever have one**

A `JScene` holds all the JObjects you would like to have in your scene. You can have as many JScenes as you'd like; just
set the active scene with `JSceneManager.setActiveScene(JScene)`

A `JCamera` decides what is shown on the JWindow relative to it's position in the scene. You can also have many of these,
but you need to set the active camera with `JSceneManager.setMainCamera(JCamera)`

The `JSceneManager` holds the active `JCamera`, `JScene` and `JWindow`
The `JSceneManager` is a thing because otherwise it would be difficult to manage the active scenes and cameras.

You can trust (i hope) that it will hold the active scene objects

****Note**: You must initialize the `JSceneManager` before using it. Do `JSceneManager.init(scene,window,camera)`

##Quick-Start
Make a JavaFX project using your preferred IDE. Copy the following code into the pre-generated `start(Stage stage)` method
###1. Set your Engine Preferences.

EnginePrefs is how you set your logging options in JEngine. It can be useful for only logging specifics such as warnings.

 I recommend the following engine preferences:
 
`EnginePrefs.logImportant = true;`

 `EnginePrefs.logInfo = true;`

 `EnginePrefs.logExtra = false;`

 `EnginePrefs.logAnnoyance = false;`

### 2. Set your app info.

JAppInfo is a way to easily access your app's info from anywhere. It can be useful for easily updating the
version and authors.

Here's and Example:

`JAppInfo.authors = new String[]{"Noah Freelove"};`

`JAppInfo.appName = "JEngine";`

`JAppInfo.appVersionMajor = 0;`

`JAppInfo.appVersionMinor = 0.0f;`

`JAppInfo.year = 2022;`

`JAppInfo.isCopyright = false;`

To log this information, use:

`JAppInfo.logInfo(false)` (false means that it will not log it as important)

### 3. Create Your Window, Scene, and Camera
Create your `JWindow` using: `JWindow window = new JWindow(1.5f, "JEngine", stage);`

* 1.5f - The window's scale (based off of 720p)
* JEngine - The window's title
* stage - The stage that `start(Stage stage)` gave you

Create your `JScene` using: `JScene scene = new JScene(window, 100, "Scene1");`

* window - The window you just created
* 100 - The maximum number of objects you plan to have in the scene
* Scene1 - The name of the scene

Create your `JCamera` using: `JCamera camera = new JCamera(new Vector3(0,0,0), 
window, scene, null, new JIdentity("Main Camera", "camera"));`

* Vector3(0,0,0) - The initial position of the camera (X,Y,Z)
* window - The window you created
* scene - The scene you created
* null - The camera's parent. I left it null because I want the camera to be stationary
* JIdentity("Main Camera", "camera") - The Camera's Identifier within the JScene.

Initialize the `JSceneManager` with: `JSceneManager.init(mainMenu,window,camera);`

### 4. Create your Sprite
Create a `JImage` using: `JImage image = new JImage(true, filepath, 128,128);`

* true - The image's default visibility;
* filepath - The filepath to the image
* 128 - The image's xSize
* 128 - The image's ySize

Create a `JSprite` using: `JSprite sprite = new JSprite(Transform.exSimpleTransform(200,200), image, 
new JIdentity("Sprite1", "sprites"));`

* Transform.exSimpleTransform(200,200) - The initial position of the sprite (x,y)
* image - The JImage you just created
* JIdentity("Sprite1", "sprites") - The Sprite's identifier in the scene. Makes it unique and therefore easy to find in
a scene object search.

### 5. Add your Camera and Sprite to the Scene
Finally, just do: `scene.add(camera);` and `scene.add(sprite);`

### 6. Setup and Start your Window
To start rendering the objects in your scene, first set the target FPS with: `window.setTargetFPS(60)`

Now to start the window do: `window.start();`

## Congrats, you created your first JEngine scene.
