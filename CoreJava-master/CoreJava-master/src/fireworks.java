    import java.awt.Graphics;
    import java.awt.Color;
    import java.awt.*;



    public class fireworks extends Frame implements Runnable {
    int nof_rockets = 0;
    int delay       = 50;
    int nof_points  = 20;
    int trail       = 10;
    int dotsize     = 2;
    int life_len    = 100;
    double g        = 0.0f;

    Thread kicker    = null;

    Rocket[] rockets = null;
    int clear        = 0;

        Color bg         = Color.black;

        public void myinit(int nr, int d, int t, int life, int np, int dot, double inG)
    {
        nof_rockets = nr;
        delay = d;
        trail = t;
        life_len = life;
        nof_points = np;
        dotsize = dot;
        g = inG;
        //g = .01f;
        rockets = new Rocket[nof_rockets];

        initrockets();

    }

    /* comment this old one out, except to tell it there are no rockets, so
     * it doesn't nullPointer me! ;)
     */

    public void init() {
        nof_rockets = 0;
        bg = new Color((float) 0.7,(float) 0.7,(float) 0.7);

    }
      /*
    int i;

    try {
      nof_rockets = Integer.parseInt(getParameter("ROCKETS"));
    } catch(Exception e) {
      nof_rockets = 3;
    }
    try {
      delay = Integer.parseInt(getParameter("DELAY"));
    } catch(Exception e) {
      delay = 50;
    }
    try {
      trail = Integer.parseInt(getParameter("TRAIL"));
    } catch(Exception e) {
      trail = 10;
    }
    try {
      life_len = Integer.parseInt(getParameter("LIFELENGTH"));
    } catch(Exception e) {
      life_len = 100;
    }
    try {
      nof_points = Integer.parseInt(getParameter("POINTS"));
    } catch(Exception e) {
      nof_points = 3;
    }
    try {
      dotsize = Integer.parseInt(getParameter("POINTSIZE"));
    } catch(Exception e) {
      dotsize = 2;
    }
    try {
      g = (double)Integer.parseInt(getParameter("GRAV")) / 1000.0f;
    } catch(Exception e) {
      g = .01f;
    }
    String bgcol = getParameter("COLOR");

    if(bgcol != null)
      {
        bg = parseCol(bgcol);
      }



    rockets = new Rocket[nof_rockets];

    initrockets();

  }

  */


    void initrockets()
    {
        int i;
        for(i = 0; i < nof_rockets; i++)
        {
            int x = 10 + (int)(Math.random() * (double)(size().width - 20));
            int y = size().height - (int)(Math.random() * 20.0f);

            double dx = (Math.random() - 0.5f) * 2.0f;
            double dy = -(Math.random() * 6);

            Color fg ;

            double cs = Math.random();

            if(cs < .33f)
            {
                fg = new java.awt.Color(155 + (int)(Math.random() * 100),
                        10, 10);
            }
            else if(cs < .66f)
            {
                fg = new java.awt.Color(155 + (int)(Math.random() * 100),
                        155 + (int)(Math.random() * 100),
                        10);
            }
            else
            {
                fg = new java.awt.Color(10, 10,
                        155 + (int)(Math.random() * 100));
            }
            rockets[i] = new Rocket(nof_points, x, y, dx, dy, g,
                    dotsize, size().width, size().height, trail, life_len,
                    fg,
                    bg);
        }
    }

    public void paint(Graphics g) {
        int i;

/*
      g.setColor(Color.white);
      g.drawRect(0, 0, size().width - 1, size().height - 1);
*/
        g.setColor(bg);
        g.fillRect(0, 0, size().width - 0, size().height - 0);

        g.clipRect(2, 2, size().width - 4, size().height - 4);

        //System.out.println(String.valueOf(nof_rockets));

        if (kicker != null) //only draw these if we're running
            for(i = 0; i < nof_rockets; i++)
            {
                rockets[i].draw(g);
            }
    }



    public void update(Graphics g)
    {
        int i;

        if(kicker != null) //used to be if (clear == 0)
        {
            g.clipRect(2, 2, size().width - 4, size().height - 4);
            for(i = 0; i < nof_rockets; i++)
            {
                rockets[i].update(g);
            }
        } else {
            g.clearRect(0, 0, size().width, size().height);
            paint(g);
            clear = 0;
        }
    }

    /* I don't want this effect
      public boolean mouseDown(java.awt.Event evt, int x, int y)
        {
          clear = 1;
          initrockets();
          return true;
        }

        */
    public void run()
    {
        int dx = 1;

        while(kicker != null) {

            repaint();

            try {
                Thread.sleep(delay);
            } catch(Exception e) {
                ;
            }
        }
    }

    public void start() //the "real" start() does nothing, because
    //I only want it started when III want it to!
    {
        stop();
    }

    public void myStart()
    {
        if(kicker == null)
        {
            kicker = new Thread(this);
            kicker.setPriority(kicker.MIN_PRIORITY);
            kicker.start();
        }
    }

    public void stop()
    {
        if (kicker != null)
            kicker.interrupt();
        kicker = null;
        repaint();
    }

    public Insets insets() {
        return new Insets(4,4,5,5);
    }


    public Dimension preferredSize()
    {
        return minimumSize();
    }

    public Dimension minimumSize()
    {
        //return new Dimension (i.getHeight(this),i.getWidth(this));
        return new Dimension (100,200);

    }

        private class Rocket {
            public Rocket(int nof_points, int x, int y, double dx, double dy, double g, int dotsize, int width, int height, int trail, int life_len, Color fg, Color bg) {
            }

            public void draw(Graphics g) {
            }

            public void update(Graphics g) {
            }
        }
    }
