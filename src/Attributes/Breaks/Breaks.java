package Attributes.Breaks;

import Attributes.Attribute.Attribute;
import ScadaBackend.FileHandling.FileHandling;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Breaks extends Attribute {
    public Breaks(float value, float minAcceptable, float maxAcceptable, float minWarning, float maxWarning) {
        super(value, minAcceptable, maxAcceptable, minWarning, maxWarning);
    }
}
