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
  [Route("/{service}")]
  public class ServiceController : Controller
  {

    [HttpGet]
    public async Task<dynamic> Get(string service)
    {
      var response = new WrappedResponse();

      dynamic result;
      try
      {
        result = await $"http://{service}/".GetAsync();
        response.response = await result.Content.ReadAsStringAsync();
      }
      catch (Exception ex)
      {
        response.response = $"Unable to talk to service {service}";
      }

      return response;
    }
  }
}

