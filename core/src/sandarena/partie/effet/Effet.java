/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sandarena.partie.effet;

import sandarena.partie.Case;

/**
 * @author Guillaume
 */
public abstract class Effet {

    public abstract void lance(Case attaquant, Case defenseur);
}
