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
        sol[3] = 7; // PPP, LPP, PLP, PPL, PLL, LPL, LLP

        // take each solution for the previous case and append P
        // take each solution for the previous case except PLL and append L
        sol[4] = 13;

        for (int i = 5; i <= n; i++) {
            int soli = (2 * (sol[i])) - sol[i - 4];

            if (soli < 0) {
                sol[i] = soli + modulo;
            } else {
                sol[i] = soli % modulo;
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
