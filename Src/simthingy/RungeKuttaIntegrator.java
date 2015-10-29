package simthingy;
public class RungeKuttaIntegrator {
    public RungeKuttaIntegrator() {
    }

    /******************************** PREFACE *******************************
         ROUTINE:  sumth_rungekutta

         PURPOSE:  A 4th order Runge-Kutta numerical integrator formulated for
              second order differential equations. Each call to the routine
              will integrate one step size (h).

         USAGE  :  void sumth_rungekutta (int       neq,
                                     double    h,
                                     double   *x,
                                     double   *y,
                                     double   *yp,
                                     double   *y2p,
                                     void      (*deriv)(double   x,
                                                        double   *y,
                                                        double   *yp,
                                                        double   *y2p))

                WHERE:   neq    number of differential equations in the set
                                to be integrated.
                         h      integration step size of independent
                                variable
                         x      independent variable for differential
                                equations. This will be reset as x = x + h
                         y      array of neq dependent variables
                         yp     array of neq first derivatives
                         y2p    array of neq second derivatives
                         deriv  address of an external routine employed
                                to compute second derivatives as a
                                function of (x, y, yp).

                                  deriv (double    x,
                                         double   *y,
                                         double   *yp,
                                         double   *y2p)

                                  where:
                                   INPUTS
                                        x    = value of independent variable
                                               fow which second derivatives
                                               are to be computed
                                        y    = array of values of dependent
                                               variables at x
                                        yp   = array of values of first
                                               derivatives at x
                                    OUTPUTS
                                        y2p  = array of values of second
                                               derivatives, to be computed by
                                               this routine as a function of
                                               x, y, yp


     ************************** END OF PREFACE *****************************/

    /**
     *
     * 4th order Runge-Kutta integrator.<br><br>
     *
     *    A 4th order Runge-Kutta numerical integrator formulated for
     *    second order differential equations. Each call to the routine
     *    will integrate one step size (h).
     *
     * @include k_sys.h
     * @iparam  neq    number of 2nd order differential equations in the
     *                 set to to be integrated.
     * @iparam  h      integration step size of independent variable
     * @ioparam x      independent variable for differential equations.
     *                 On entry this is the current value. On exit, it
     *                 will have been reset as x = x + h
     * @ioparam y      array of neq dependent variables. On entry these
     *                 will be values corresponding to the value of x.
     *                 On exit they will correspond to x+h.
     * @ioparam yp     array of neq dependent variable first derivatives.
     *                 On entry these will be values corresponding to the
     *                 value of x. On exit they will correspond to x+h.
     * @oparam  y2p    array of neq dependent variable second derivatives.
     *                 On exit these values will correspond to x+h.
     * @iparam  deriv  pointer to a function used to compute second derivatives
     *                 from values of the independent variable and the
     *                 dependent variables and their first derivatives.
     *
     * @return  none
     *
     * @author  03/01/02  rhp initial version
     *
    void sumth_rungekutta(int neq,
                          double h,
                          double * x,
                          double * y,
                          double * yp,
                          double * y2p,
                          void ( * deriv) (double x,
                                           double * y,
                                           double * yp,
                                           double * y2p)) {
        double * temp;
        double * k1, * k2, * k3, * k4;
        double * y1, * y2, * y3, * y4;
        double * yp1, * yp2, * yp3, * yp4;
        double x1, x2, x3, x4;
        int i;

        // allocate dynamic memory
        temp = (double * ) malloc(12 * neq * sizeof(double));
        k1 = temp;
        k2 = & temp[neq];
        k3 = & temp[2 * neq];
        k4 = & temp[3 * neq];
        y1 = & temp[4 * neq];
        y2 = & temp[5 * neq];
        y3 = & temp[6 * neq];
        y4 = & temp[7 * neq];
        yp1 = & temp[8 * neq];
        yp2 = & temp[9 * neq];
        yp3 = & temp[10 * neq];
        yp4 = & temp[11 * neq];

        x1 = * x;
        x2 = * x + h / 2.;
        x3 = * x + h / 2.;
        x4 = * x + h;

        // if y2p not input
        // deriv (x1, y, yp, k1);
        for (i = 0; i < neq; i++) {
            y1[i] = y[i];
            yp1[i] = yp[i];
            k1[i] = y2p[i];

            y2[i] = y[i] + h * yp[i] / 2. + h * h * k1[i] / 8.;
            yp2[i] = yp[i] + h * k1[i] / 2.;
        }

        // derivatives at x + h/2
        deriv(x2, y2, yp2, k2);

        for (i = 0; i < neq; i++) {
            y3[i] = y2[i];
            yp3[i] = yp1[i] + h * k2[i] / 2.;
        }

        // refine derivatives at x + h/2
        deriv(x3, y3, yp3, k3);

        for (i = 0; i < neq; i++) {
            y4[i] = y[i] + h * yp[i] + h * h * k3[i] / 2.;
            yp4[i] = yp[i] + h * k3[i];
        }

        // derivatives at x + h
        deriv(x4, y4, yp4, k4);

        for (i = 0; i < neq; i++) {
            y[i] = y[i] + h * yp[i] + h * h * (k1[i] + k2[i] + k3[i]) / 6.;
            yp[i] = yp[i] + h * (k1[i] + 2. * k2[i] + 2. * k3[i] + k4[i]) / 6.;
        }

        // refine derivatives at x + h
        deriv(x4, y, yp, y2p);

        // reset independent variable
        * x = x4;

        // free dynamic memory
        free(temp);
    }

     */
}
