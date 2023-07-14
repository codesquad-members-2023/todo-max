import { rest, MockedRequest, ResponseComposition, restContext } from "msw";
import index from "./index.json";
import history from "./history.json";

export const handlers: Array<ReturnType<(typeof rest)["get"]>> = [
  rest.get(
    "/index",
    async (
      req: MockedRequest,
      res: ResponseComposition,
      ctx: typeof restContext
    ) => {
      await sleep(200);

      return res(ctx.status(200), ctx.json(index));
    }
  ),
  rest.get(
    "/history",
    async (
      req: MockedRequest,
      res: ResponseComposition,
      ctx: typeof restContext
    ) => {
      await sleep(200);

      return res(ctx.status(200), ctx.json(history));
    }
  ),
];

async function sleep(timeout: number): Promise<void> {
  return new Promise<void>((resolve) => {
    setTimeout(resolve, timeout);
  });
}
