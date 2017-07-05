// NQueens.cpp : Defines the entry point for the console application.
//

#include <iostream>

using namespace std;

typedef struct _Point {
    int X;
    int Y;
} Point;

int solutionCount = 0;

#define GRID_SIZE   10
#define SAME_DIAGONAL(pt1, pt2) \
    (labs(pt1.X - pt2.X) == labs(pt1.Y - pt2.Y))

void PrintArray(int *Array)
{
    cout << solutionCount << ": ";
    for (int i = 0; i < GRID_SIZE; i++)
    {
        cout << "(" << i << ", " << Array[i] << "); ";
    }
    cout << endl;
}

bool IsFreeColumn(int RowIndex, int ColumnIndex, int *UsedColumns)
{
    // Only loop until the last row where a queen was placed. Rest of the 
    // array is unfilled yet, and we can assume is open for queen placement
    for (int ro = 0; ro < RowIndex; ro++)
    {
        Point usedPoint = { ro, UsedColumns[ro] };
        Point testPoint = { RowIndex, ColumnIndex };

        if ((UsedColumns[ro] == ColumnIndex) ||
            SAME_DIAGONAL(usedPoint, testPoint))
        {
            return false;
        }
    }
    return true;
}

void FindQueenSpots(int Row, int *QueenColumns)
{
    if (Row == GRID_SIZE)
    {
        solutionCount++;
        PrintArray(QueenColumns);
        return;
    }
    // Walk through each column and see if that column for the current row
    // can be used for placing a queen. This can be done iff:
    // a. There is no other queen in the current column, and
    // b. There is no other queen in the same diagonal
    for (int co = 0; co < GRID_SIZE; co++)
    {
        if (true == IsFreeColumn(Row, co, QueenColumns))
        {
            QueenColumns[Row] = co;
            FindQueenSpots((Row + 1), QueenColumns);
        }
    }
}


int main()
{
    int queenCells[GRID_SIZE]; // Columns where queen can be placed
    for (int i = 0; i < GRID_SIZE; i++)
    {
        // Initialize all used columns to -1
        queenCells[i] = -1;
    }

    FindQueenSpots(0, queenCells);

    return 0;
}