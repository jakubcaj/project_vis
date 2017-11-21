// const HTMLWebpackPlugin = require('html-webpack-plugin');
// const HTMLWebpackPluginConfig = new HTMLWebpackPlugin({
//     template: './src/main/webapp/WEB-INF/pages/hello.html',
//     filename: 'hello.html',
//     inject: 'body'
// });

module.exports = {
    entry: './src/main/webapp/resources/js/hello.jsx',
    module: {
        loaders: [
            {
                test: /\.jsx$/,
                exclude: /node-modules/,
                loader: 'babel-loader'
            }
        ]
    },
    output: {
        filename: 'hello.js',
        path: '/Users/jakub/IdeaProjects/project_vis/src/main/webapp/resources/components/'
    }//,
    // plugins: [HTMLWebpackPluginConfig]
};