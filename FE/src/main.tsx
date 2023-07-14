import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App.tsx";
import { worker } from "./mocks/browers.ts";
import "/src/styles/main.css";

if (process.env.NODE_ENV === "development") {
  worker.start();
}

ReactDOM.createRoot(document.getElementById("root")!).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);
