<?php

namespace develnext\bundle\okhttp;

use ide\bundle\AbstractBundle;
use ide\bundle\AbstractJarBundle;
use ide\project\Project;
use ide\library\IdeLibraryBundleResource;

/**
 * Class WinFX
 * @package develnext\bundle\okhttp
 */
class OkHttp extends AbstractJarBundle
{
    /**
     * @param Project $project
     * @param AbstractBundle|null $owner
     */
    public function onAdd(Project $project, AbstractBundle $owner = null){
        parent::onAdd($project, $owner);
    }

    /**
     * @param Project $project
     * @param AbstractBundle|null $owner
     */
    public function onRemove(Project $project, AbstractBundle $owner = null){
        parent::onRemove($project, $owner);
    }

    /**
     * @param IdeLibraryBundleResource $resource
     */
    public function onRegister(IdeLibraryBundleResource $resource)
    {
        parent::onRegister($resource);
    }
}
