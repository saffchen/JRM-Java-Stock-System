import vue from '@vitejs/plugin-vue';
import path from 'path';

// https://vitejs.dev/config/
export default {
  plugins: [vue()],
  resolve: {
    extensions: [
      ".js",
      ".json",
      ".vue"
    ],
    alias: {
      '@': path.resolve(__dirname, './src')
    }
  },
  server: {
    host: true,
    port: 3000
  },
  
};
