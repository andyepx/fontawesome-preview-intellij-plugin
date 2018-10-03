package com.andreaepifani.fontawesomeutils

import com.intellij.openapi.util.IconLoader
import java.awt.Image
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import javax.imageio.ImageIO
import javax.swing.Icon

class IconLookup {

    private var allIcons: List<String>
//    private var allIconsData: Map<String, Icon>

    init {
        allIcons = getResourceFiles("/icons/").map { String.format("fa-%s", it.replace(".svg", "")) }
//        allIconsData = allIcons.map { it to IconLoader.getIcon(iconDataAsImage(it)!!) }.toMap()
    }

    fun getIcon(icon: String): Icon {
        return IconLoader.getIcon(iconDataAsImage(icon)!!)
    }

    fun contains(icon: String): Boolean {
        return allIcons.contains(icon)
    }

    fun iconData(icon: String): String {
        val iconName = "${icon.replace("fa-", "")}.svg"
        val c = getResourceAsStream("/icons/$iconName")!!.readBytes()
        val doc = String(c)
        return doc.replace("<svg ", "<svg width=\"32\" height=\"32\" ")
    }

    private fun iconDataAsImage(icon: String): Image? {
        val iconName = "${icon.replace("fa-", "")}.svg"
        val image = ImageIO.read(getResourceAsURL("/icons/$iconName")!!)
        return image.getScaledInstance(32, 32, 100)
    }

    private fun getResourceFiles(path: String): List<String> = getResourceAsStream(path).use {
        return if (it == null) emptyList()
        else BufferedReader(InputStreamReader(it)).readLines()
    }

    private fun getResourceAsStream(resource: String): InputStream? =
            Thread.currentThread().contextClassLoader.getResourceAsStream(resource)
                    ?: resource::class.java.getResourceAsStream(resource)

    private fun getResourceAsURL(resource: String): URL? =
            Thread.currentThread().contextClassLoader.getResource(resource)
                    ?: resource::class.java.getResource(resource)

}