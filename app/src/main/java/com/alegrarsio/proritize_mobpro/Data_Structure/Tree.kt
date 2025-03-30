package com.alegrarsio.proritize_mobpro.Data_Structure

import com.alegrarsio.proritize_mobpro.model.Node
import com.alegrarsio.proritize_mobpro.model.Project

class Tree {

    var root : Node? = null;

    fun insert(project : Project)
    {
        root = InserHelper(root,project);
    }

    fun inOrder() : List<Project>{
        val hasil = mutableListOf<Project>();
        InOrderHelper(root,hasil);
        return hasil;
    }

    private fun InserHelper(current : Node?, project: Project) : Node{

        if (current == null){
            return Node(project.priority).apply { projects.add(project) }
        }
        if (project.priority < current.priority)
            current.left = InserHelper(current.left , project);

        else if(project.priority > current.priority)
            current.right = InserHelper(current.right , project)

        else
            current.projects.add(project)

        return current;
    }

    private fun InOrderHelper(node : Node? , list : MutableList<Project>)
    {
        if (node != null)
        {
            InOrderHelper(node.right,list)
            list.addAll(node.projects)
            InOrderHelper(node.left,list)
        }
    }


}