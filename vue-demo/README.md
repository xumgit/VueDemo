# vue-demo

> A Vue.js project

## Build Setup

``` bash
# how build vue project by vue-cli
npm install --global vue-cli
vue init webpack projectName

# install plugin, element-ui | axios | moment
npm i element-ui -S
npm install axios --save
npm install vue-moment --save

# install dependencies
npm install

# serve with hot reload at localhost:8087
npm run dev

# build for production with minification
npm run build

# build for production and view the bundle analyzer report
npm run build --report

# build for production
1. when bom run build, you should be find in src/utils/requests.js file `baseURL`, then annotation, because proxyTable
2. copy dist/ file to you project
for example: run springboot project,
IndexController.java following code:

@Controller
@RequestMapping("/index")
public class IndexController {

    @RequestMapping("/indexVue")
    public String indexVue() {
        return "vue/index";
    }
}

then input url, http://localhost:8082/index/indexVue

before input url, you should be copy `dist/static` file to `resources/static/index/` directory.
because root directory is `index``[because controller @RequestMapping("/index")]`,
not `/` directory, so copy file to `resources/static/index/`.
then copy `dist/index.html` to `templates/vue/`.
```



For a detailed explanation on how things work, check out the [guide](http://vuejs-templates.github.io/webpack/) and [docs for vue-loader](http://vuejs.github.io/vue-loader).
