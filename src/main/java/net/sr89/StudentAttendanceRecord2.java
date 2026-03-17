package net.sr89;

/**
 * https://leetcode.com/problems/student-attendance-record-ii/
 */
public class StudentAttendanceRecord2 {
    private static final int modulo = 1000000007;

    public int checkRecord(int n) {
        if (n == 1) {
            return 3;
        }

        if (n == 2) {
            return 8;
        }

        // solutions ignoring absences
        int[] sol = new int[100005];

        sol[0] = 1; // only one way to have 0 elements
        sol[1] = 2; // P, L
        sol[2] = 4; // PP, LP, PL, LL
        sol[3] = 7; // PPP, PLP, PPL, PLL, LPP, LPL, LLP
        sol[4] = sol[3] * 2; // take each solution for the previous case and append either P or L

        for (int i = 5; i <= n; i++) {
            int solN = (2 * (sol[i])) - sol[i - 5];

            if (solN < 0) {
                sol[i] = solN + modulo;
            } else {
                sol[i] = solN % modulo;
            }
        }

        // now insert the absence in each location
        int allSolutions = 0;

        for (int i = 1; i <= n; i++) {
            int solutionsWithAbsenceAtI = sol[i - 1] * sol[n - i];
            solutionsWithAbsenceAtI = solutionsWithAbsenceAtI % modulo;
            allSolutions += solutionsWithAbsenceAtI;
            allSolutions = allSolutions % modulo;
        }

        return (allSolutions + sol[n]) % modulo;
    }
}
