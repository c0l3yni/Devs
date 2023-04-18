const HtmlWebPackPlugin = require("html-webpack-plugin");
const webpack = require('webpack')
const htmlPlugin = new HtmlWebPackPlugin({
    template: "./public/index.html",
    filename: "./index.html",
});
module.exports = {
    mode: "development",
    devServer: {
        port: 3000,
        historyApiFallback: true,
    },
    output: {
        publicPath: '/'
    },
    module: {
        rules: [
            {
                test: /\.(js|jsx)$/,
                exclude: /node_modules/,
                use: {
                    loader: "babel-loader",
                    options: {
                        presets: [
                            [
                                "@babel/preset-env",
                                {
                                    targets: "defaults",
                                },
                            ],
                            "@babel/preset-react",
                        ],
                    },
                },
            },
            {
                test: /\.css$/,
                use: ["style-loader", "css-loader"],
            },
            {
                test: /\.png$/,
                use: 'file-loader'
            }
        ],

    },
    resolve: {
        extensions: [".js", ".jsx"],
        fallback: {
            "https": require.resolve("https-browserify"),
            "http": require.resolve("stream-http"),
            "stream": require.resolve("stream-browserify"),
            "assert": require.resolve("assert"),
            "zlib": require.resolve("browserify-zlib"),
            "buffer": require.resolve("buffer"),
            "util": require.resolve("util")
        }
    },
    plugins: [htmlPlugin, new webpack.ProvidePlugin({
        process: 'process/browser',
    }),],

};
