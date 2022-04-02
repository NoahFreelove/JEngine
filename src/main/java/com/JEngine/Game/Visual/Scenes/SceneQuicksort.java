package com.JEngine.Game.Visual.Scenes;

import com.JEngine.PrimitiveTypes.ObjRef;

public class SceneQuicksort {
    // Quicksort implementation
    public static void quickSortZ(ObjRef arr[], int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);
            quickSortZ(arr, begin, partitionIndex-1);
            quickSortZ(arr, partitionIndex+1, end);
        }
    }

    private static int partition(ObjRef arr[], int begin, int end) {
        if(arr[end] == null)
            return end;
        int pivot = (int)arr[end].objRef.getTransform().position.z;
        int i = (begin-1);

        for (int j = begin; j < end; j++) {
            if(arr[j] == null)
            {
                i++;
                continue;
            }

            if ((int)arr[j].objRef.getTransform().position.z <= pivot) {
                i++;

                ObjRef swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }

        ObjRef swapTemp = arr[i+1];
        arr[i+1] = arr[end];
        arr[end] = swapTemp;

        return i+1;
    }
}
