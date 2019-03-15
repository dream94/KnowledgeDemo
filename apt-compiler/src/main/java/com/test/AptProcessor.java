package com.test;

import com.google.auto.service.AutoService;
import com.internal.Print;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

/**
 * Created by cc on 2019/2/6.
 */
@AutoService(Processor.class)
public class AptProcessor extends AbstractProcessor {
    private Types mTypeUtils;
    private Elements mElementUtils;
    private Filer mFiler;
    private Messager mMessager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);

        //初始化我们需要的基础工具
        mTypeUtils = processingEnv.getTypeUtils();
        mElementUtils = processingEnv.getElementUtils();
        mFiler = processingEnv.getFiler();
        mMessager = processingEnv.getMessager();
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        //支持的java版本
        return SourceVersion.RELEASE_7;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        //支持的注解
        Set<String> annotations = new LinkedHashSet<>();
        annotations.add(Print.class.getCanonicalName());
        return annotations;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {//这里开始处理我们的注解解析了，以及生成Java文件
        if (set == null || set.isEmpty()) {
            info(">>> set is null... <<<");
            return true;
        }
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(Print.class);

        if (elements == null || elements.isEmpty()) {
            info(">>> elements is null... <<<");
            return true;
        }
        // 遍历所有被注解了@Print的元素
        for (Element annotatedElement : elements) {
            if (annotatedElement.getKind() != ElementKind.CLASS) {
                error(annotatedElement, "Only classes can be annotated with @%s",
                        Print.class.getSimpleName());
                return true; // 退出处理
            }
            analysisAnnotated(annotatedElement);
        }

        return true;
    }

    private static final String SUFFIX = "$$Test";

    private void analysisAnnotated(Element classElement) {
        Print annotation = classElement.getAnnotation(Print.class);
        String text = annotation.text();

        String newClassName = "A" + SUFFIX;

        StringBuilder builder = new StringBuilder()
                .append("package com.example.cc.knowldegedemo.auto;\n\n")
                .append("public class ")
                .append(newClassName)
                .append(" {\n\n") // open class
                .append("\tpublic String getMessage() {\n") // open method
                .append("\t\treturn \"");

        // this is appending to the return statement
        builder.append(text).append(text).append(" !\\n");

        builder.append("\";\n") // end return
                .append("\t}\n") // close method
                .append("}\n"); // close class

        try { // write the file
            JavaFileObject source = mFiler.createSourceFile("com.example.cc.knowldegedemo.auto." + newClassName);
            Writer writer = source.openWriter();
            writer.write(builder.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            // Note: calling e.printStackTrace() will print IO errors
            // that occur from the file already existing after its first run, this is normal
        }
    }

    private void error(Element e, String msg, Object... args) {
        mMessager.printMessage(
                Diagnostic.Kind.ERROR,
                String.format(msg, args),
                e);
    }

    private void info(String msg, Object... args) {
        mMessager.printMessage(
                Diagnostic.Kind.NOTE,
                String.format(msg, args));
    }
}
