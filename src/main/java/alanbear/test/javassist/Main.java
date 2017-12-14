package alanbear.test.javassist;

import alanbear.test.javassist.annotation.MyAnnotation;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.Annotation;

@MyAnnotation
public class Main {

    private static final ClassPool classPool = ClassPool.getDefault();

    public static void main(String[] args) throws Exception {

//        CtClass cc = classPool.makeClass("alanbear.test.javassist.TestClass");
        CtClass cc = classPool.get("alanbear.test.javassist.NewClass");

        ClassFile cf = cc.getClassFile();
        ConstPool cp = cf.getConstPool();

        AnnotationsAttribute attr = new AnnotationsAttribute(cp, AnnotationsAttribute.visibleTag);
        Annotation annot = new Annotation("alanbear.test.javassist.annotation.MyAnnotation",cp);
        Annotation annot2 = new Annotation("alanbear.test.javassist.annotation.MyAnnotation2",cp);
        attr.addAnnotation(annot);
        attr.addAnnotation(annot2);
        cf.addAttribute(attr);

        cc.writeFile("./out/production/classes");

        Class clazz = NewClass.class;

        ClassLoader loader = Main.class.getClassLoader();
        System.out.println(loader.getResource("alanbear/test/javassist/Main.class"));

        System.out.println(clazz.getAnnotations().length);

    }

}
