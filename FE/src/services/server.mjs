import express from "express";
import cors from "cors";
import fs from "fs";

const app = express();
const port = 8080;

app.use(cors());

app.get("/index", (req, res) => {
  const data = fs.readFileSync("./index.json", "utf8");
  res.json(JSON.parse(data));
});

app.get("/history", (req, res) => {
  const data = fs.readFileSync("./history.json", "utf8");
  res.json(JSON.parse(data));
});

app.listen(port, () => {
  console.log(`Server listening at http://localhost:${port}`);
});
