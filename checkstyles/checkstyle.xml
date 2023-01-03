<?xml version="1.0"?>
<!DOCTYPE module PUBLIC
        "-//Puppy Crawl//DTD Check Configuration 1.3//EN"
        "http://www.puppycrawl.com/dtds/configuration_1_3.dtd">

<module name="Checker">
    <property name="charset" value="UTF-8"/>
    <property name="localeLanguage" value=""/>
    <property name="localeCountry" value=""/>

    <module name="SuppressionFilter">
        <property name="file" value="checkstyles/checkstyle-suppressions.xml"/>
    </module>
    <!--Add more checks from google style guide:-->
    <!--https://github.com/checkstyle/checkstyle/blob/master/src/main/resources/google_checks.xml-->
    <module name="TreeWalker">
        <property name="tabWidth" value="2"/>
        <module name="PackageName">
            <property name="format" value="^[a-z]+(\.[a-z][a-z0-9]*)*$"/>
        </module>
        <module name="IllegalImport">
            <property name="illegalPkgs" value="junit"/>
            <property name="illegalClasses" value="java.util.logging.Logger"/>
        </module>
        <module name="RedundantImport"/>
        <module name="AvoidStarImport"/>
        <module name="UnusedImports">
            <property name="processJavadoc" value="true"/>
        </module>

        <module name="ParenPad"/>
        <module name="MethodParamPad"/>
        <module name="NoWhitespaceAfter"/>
        <module name="NoWhitespaceBefore"/>
        <module name="WhitespaceAfter">
        </module>
        <module name="WhitespaceAround">
        </module>

        <module name="ModifierOrder"/>
        <module name="RedundantModifier"/>

        <module name="LeftCurly"/>
        <module name="SuperClone"/>
        <module name="SuperFinalize"/>
        <module name="DefaultComesLast"/>
        <module name="MultipleVariableDeclarations"/>
        <module name="UnnecessaryParentheses"/>
        <module name="ClassFanOutComplexity">
            <property name="max" value="30"/>
        </module>
        <module name="CyclomaticComplexity">
            <property name="max" value="11"/>
        </module>
        <module name="NPathComplexity"/>


        <module name="ArrayTypeStyle"/>
        <module name="Indentation">
            <property name="basicOffset" value="2"/>
            <property name="caseIndent" value="2"/>
            <property name="throwsIndent" value="2"/>
            <property name="arrayInitIndent" value="2"/>
            <property name="lineWrappingIndentation" value="2"/>
        </module>
    </module>

    <module name="LineLength">
        <property name="ignorePattern" value="^ *\* *@see.+$"/>
        <property name="max" value="136"/>
    </module>

    <module name="NewlineAtEndOfFile">
        <property name="lineSeparator" value="lf"/>
    </module>
    <module name="FileTabCharacter"/>
</module>
