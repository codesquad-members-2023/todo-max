import { setupWorker } from "msw";
import { handlers } from "./Apis";

export const worker = setupWorker(...handlers);
