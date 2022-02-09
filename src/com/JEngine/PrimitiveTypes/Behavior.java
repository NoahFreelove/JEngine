package com.JEngine.PrimitiveTypes;

/** JEngine.Behavior (c) Noah Freelove
 * Brief Explanation:
 * A Behavior is an interface used by JWindow to run functions every update.
 *
 * Example:
 * ------------
 * Behavior behavior = (int totalFrameCount) -> {Player.Move(Direction.Up, 50); }
 * window.AddUpdateBehavior(behavior);
 * ------------
 * This will move the player every frame
 *
 * int totalFrameCount is an argument to allow for easy testing
 * **/

public interface Behavior {
    void behave(int totalFrameCount);
}
