package org.glygen.ggarray.glycan.basetype;

import org.eurocarbdb.MolecularFramework.io.SugarImporterException;
import org.eurocarbdb.MolecularFramework.io.GlycoCT.SugarExporterGlycoCTCondensed;
import org.eurocarbdb.MolecularFramework.io.GlycoCT.SugarImporterGlycoCTCondensed;
import org.eurocarbdb.MolecularFramework.sugar.GlycoconjugateException;
import org.eurocarbdb.MolecularFramework.sugar.Sugar;
import org.eurocarbdb.MolecularFramework.util.visitor.GlycoVisitorException;

public class GlycanBaseTypeUtilTest
{

    public static void main(String[] args)
            throws SugarImporterException, GlycoconjugateException, GlycoVisitorException
    {
        String t_glycoCT = "RES\n1b:a-dgal-HEX-1:5\n2b:b-dglc-HEX-1:5\n3s:n-acetyl\n4b:b-dgal-HEX-1:5\nLIN\n1:1o(3+1)2d\n2:2d(2+1)3n\n3:2o(4+1)4d";
        // create the Sugar object
        SugarImporterGlycoCTCondensed t_importer = new SugarImporterGlycoCTCondensed();
        SugarExporterGlycoCTCondensed t_exporter = new SugarExporterGlycoCTCondensed();
        Sugar t_sugar = t_importer.parse(t_glycoCT);
        // check if the monosaccharide is an alditol
        if (!GlycanBaseTypeUtil.isMakeBaseTypePossible(t_sugar))
        {
            // tell the user and ask him to provide a closed ring version
            return;
        }
        /**
         * IMPORTANT: We are manipulating monosaccharide object that is part of
         * the sugar. NOT a copy. Any change done effects the sugar object.
         **/
        // make basetype (unknown anomer)
        GlycanBaseTypeUtil.makeBaseType(t_sugar);
        t_exporter.start(t_sugar);
        System.out.println("BASETYPE:\n" + t_exporter.getHashCode());
        // make alpha version
        GlycanBaseTypeUtil.makeAlpha(t_sugar);
        t_exporter.start(t_sugar);
        System.out.println("Alpha version:\n" + t_exporter.getHashCode());
        // make beta version
        GlycanBaseTypeUtil.makeBeta(t_sugar);
        t_exporter.start(t_sugar);
        System.out.println("Beta version:\n" + t_exporter.getHashCode());
        // make alditol version
        GlycanBaseTypeUtil.makeAlditol(t_sugar);
        t_exporter.start(t_sugar);
        System.out.println("Alditol version:\n" + t_exporter.getHashCode());
    }

}
