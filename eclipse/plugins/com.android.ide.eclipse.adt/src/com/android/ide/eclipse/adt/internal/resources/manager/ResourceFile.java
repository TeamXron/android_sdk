/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Eclipse Public License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.eclipse.org/org/documents/epl-v10.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.ide.eclipse.adt.internal.resources.manager;

import com.android.ide.common.rendering.api.ResourceValue;
import com.android.ide.eclipse.adt.internal.resources.ResourceType;
import com.android.ide.eclipse.adt.internal.resources.configurations.FolderConfiguration;
import com.android.sdklib.io.IAbstractFile;

import java.util.Collection;

/**
 * Represents a Resource file (a file under $Project/res/)
 */
public abstract class ResourceFile extends Resource {

    private final IAbstractFile mFile;
    private final ResourceFolder mFolder;

    protected ResourceFile(IAbstractFile file, ResourceFolder folder) {
        mFile = file;
        mFolder = folder;
    }

    /*
     * (non-Javadoc)
     * @see com.android.ide.eclipse.editors.resources.manager.Resource#getConfiguration()
     */
    @Override
    public FolderConfiguration getConfiguration() {
        return mFolder.getConfiguration();
    }

    /**
     * Returns the IFile associated with the ResourceFile.
     */
    public final IAbstractFile getFile() {
        return mFile;
    }

    /**
     * Returns the parent folder as a {@link ResourceFolder}.
     */
    public final ResourceFolder getFolder() {
        return mFolder;
    }

    /**
     * Returns whether the resource is a framework resource.
     */
    public final boolean isFramework() {
        return mFolder.isFramework();
    }

    /**
     * Returns the list of {@link ResourceType} generated by the file.
     */
    public abstract ResourceType[] getResourceTypes();

    /**
     * Returns whether the file generated a resource of a specific type.
     * @param type The {@link ResourceType}
     */
    public abstract boolean hasResources(ResourceType type);

    /**
     * Get the list of {@link ProjectResourceItem} of a specific type generated by the file.
     * This method must make sure not to create duplicate.
     * @param type The type of {@link ProjectResourceItem} to return.
     * @param projectResources The global Project Resource object, allowing the implementation to
     * query for already existing {@link ProjectResourceItem}
     * @return The list of <b>new</b> {@link ProjectResourceItem}
     * @see ProjectResources#findResourceItem(ResourceType, String)
     */
    public abstract Collection<ProjectResourceItem> getResources(ResourceType type,
            ProjectResources projectResources);

    /**
     * Returns the value of a resource generated by this file by {@link ResourceType} and name.
     * <p/>If no resource match, <code>null</code> is returned.
     * @param type the type of the resource.
     * @param name the name of the resource.
     */
    public abstract ResourceValue getValue(ResourceType type, String name);

    @Override
    public String toString() {
        return mFile.toString();
    }
}

