using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Threading;
using Microsoft.AspNetCore.Mvc;
using Flurl.Http;
using dotnetcore.Models;

namespace dotnetcore.Controllers
{
  [Route("")]
  public class RootController : Controller
  {


    // GET api/values
    [HttpGet]
    public async Task<Response> GetAsync()
    {
      Random r = new Random();
      var sleepTime = r.Next(0, 1000);
      var requestTime = r.Next(0, 4);

      try
      {
        if (sleepTime > 900)
        {
          throw new ApplicationException("I cant wait that long!");
        }
        Thread.Sleep(sleepTime);
      }
      catch (Exception ex)
      {
      }

        await $"https://httpbin.org/delay/{requestTime}".GetAsync();

      return new Response { sleep = sleepTime, requestTime = requestTime };
    }
  }
}
