package com.andreaepifani.fontawesome;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.xml.XmlToken;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class SimpleLineMarkerProvider extends RelatedItemLineMarkerProvider {
    @Override
    protected void collectNavigationMarkers(@NotNull PsiElement element,
                                            Collection<? super RelatedItemLineMarkerInfo> result) {
        if (element instanceof XmlToken) {
            String value = element.getText();
            if (value != null && value.contains("fa-")) {
                result.add(
                        NavigationGutterIconBuilder
                                .create(SimpleIcons.FILE)
                                .setTarget(element)
                                .createLineMarkerInfo(element)
                );
            }
        }
    }
}