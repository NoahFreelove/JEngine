package com.JEngine.Core.Position;

public class DirectionAngleConversion {

    public static Direction vecToDir(Vector2 vec)
    {
        // convert to angle
        return switch ((int) vec.x) {
            case 45 -> Direction.UpRight;
            case 90 -> Direction.Right;
            case 135 -> Direction.DownRight;
            case 180 -> Direction.Down;
            case 225 -> Direction.DownLeft;
            case 270 -> Direction.Left;
            case 315 -> Direction.UpLeft;
            default -> Direction.Up;
        };
    }
}
